package com.login.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MyAuthenticationSuccessHandler getSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public MyAuthenticationFailHandler getFailHandler(){
        return new MyAuthenticationFailHandler();
    }

    @Autowired
    MyAuthenticationSuccessHandler successHandler;
    @Autowired
    MyAuthenticationFailHandler  failHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginProcessingUrl("/authentication/logins")  // 自定义的登录接口
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()
                // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/authentication/logins","/error").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();
         }




}
