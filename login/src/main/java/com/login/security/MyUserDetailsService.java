package com.login.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.demo.TbMasDoctor;
import com.login.Service.TbMasDoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        return  new User(username,user.getDtPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
