package com.login.security;

import com.alibaba.fastjson.JSON;
import com.common.demo.ApiCode;
import com.common.demo.Result;
import com.login.demo.SecurityUser;
import com.login.redis.RedisHandle;
import com.login.utlis.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * json 转换工具类
     */
    private ObjectMapper objectMapper= new ObjectMapper();;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    RedisHandle redisHandle;

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            logger.info("登录成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.newSuccess(authentication, ApiCode.Public_SUCCESSFULLY,refresh())));


    }

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

