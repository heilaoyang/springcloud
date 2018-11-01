package com.login.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.demo.TbMasDoctor;
import com.login.Service.TbMasDoctorService;
import com.login.demo.SecurityUser;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TbMasDoctorService doctorService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbMasDoctor user = doctorService.selectOne(new EntityWrapper<TbMasDoctor>().eq("DT_PHONE", username));
        if (user==null){
            user=doctorService.selectOne(new EntityWrapper<TbMasDoctor>().eq("DT_PHONE", username));
        }
        if (user==null){
            throw new UsernameNotFoundException(username + " not found");
        }
        Set<GrantedAuthority> set=new HashSet<GrantedAuthority>();
        //角色权限控制以ROLE_开头
        set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        set.add(new SimpleGrantedAuthority("ROLE_Public"));
        SecurityUser securityUser = new SecurityUser(user.getDtWorkid(), user.getDtPassword(),set);
        securityUser.setLastPasswordResetDate(user.getModifytime());
        return securityUser;
    }
}
