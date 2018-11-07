package com.login.Service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.demo.TbMasPatient;
import com.login.Service.TbMasDoctorService;
import com.login.dao.TbMasPatientMapper;
import com.login.demo.SecurityUser;
import com.login.redis.RedisHandle;
import com.login.utlis.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-10-29
 */
@Service
public class TbMasDoctorServiceImpl extends ServiceImpl<TbMasPatientMapper, TbMasPatient> implements TbMasDoctorService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private TbMasPatientMapper tbMasPatientMapper;

    @Autowired
    RedisHandle redisHandle;
    @Autowired
    ObjectMapper objectMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.expiration}")
    private Long expiration;
    @Autowired
    public TbMasDoctorServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            TbMasPatientMapper userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tbMasPatientMapper = userRepository;
    }

    @Override
    public SecurityUser register(SecurityUser userToAdd) {
        return null;
    }

    @Override
    public String login(String username, String password) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        return generateToken(username);
    }

    public String loginByPhone(String phone,HttpServletRequest request){
        TbMasPatient user = this.selectOne(new EntityWrapper<TbMasPatient>().eq("PT_PHONE", phone));
        if (user==null){
            return null;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getPtLoginid());
        return generateToken(userDetails);
    }

    public String generateToken(String username){
        UserDetails userDetails;
        userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails==null){
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        final String token = tokenHead+jwtTokenUtil.generateToken(userDetails);
        String oldtoken= (String) redisHandle.get(tokenHead + userDetails.getUsername());
        //保证账号同时只可一人在线
        redisHandle.set(tokenHead+userDetails.getUsername(),token,expiration);
        if(!StringUtils.isEmpty(oldtoken)){
            redisHandle.remove(oldtoken);
        }
        //通过redis保存token实现单点登录
        redisHandle.set(token, JSON.toJSONString(userDetails),expiration);
        return token;
    }
    public String generateToken(UserDetails userDetails){
        final String token = tokenHead+jwtTokenUtil.generateToken(userDetails);
        String oldtoken= (String) redisHandle.get(tokenHead + userDetails.getUsername());
        //保证账号同时只可一人在线
        redisHandle.set(tokenHead+userDetails.getUsername(),token,expiration);
        if(!StringUtils.isEmpty(oldtoken)){
            redisHandle.remove(oldtoken);
        }
        //通过redis保存token实现单点登录
        redisHandle.set(token, JSON.toJSONString(userDetails),expiration);
        return token;
    }



    @Override
    public String refresh() {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oldtoken= (String) redisHandle.get(tokenHead + user.getUsername());
        final String newtoken = tokenHead + jwtTokenUtil.generateToken(user);
        redisHandle.set(tokenHead + user.getUsername(), newtoken, expiration);
        if (!StringUtils.isEmpty((String) redisHandle.get(oldtoken))) {
            redisHandle.remove(oldtoken);
        }
        redisHandle.set(newtoken, JSON.toJSONString(user), expiration);
        return newtoken;
    }
}
