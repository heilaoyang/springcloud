package com.login.security;


import com.common.demo.ApiCode;
import com.common.demo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.fiter.JwtAuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    private ObjectMapper objectMapper= new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(getClass());


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

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    MyAuthenticationSuccessHandler successHandler;
    @Autowired
    MyAuthenticationFailHandler  failHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()//  定义当需要用户登录时候，转到的登录页面。
                .loginProcessingUrl("/authentication/login")  // 自定义的登录接口
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()
                // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/authentication/logins","/error").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()// 任何请求,登录后可以访问
                .authenticated()
                .and()
                .logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .csrf().disable();
        http.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }



    /**
     * 登出成功后的处理
     */
    public  class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.newFailure(ApiCode.LOGOUT_SUCCESSFULLY)));        }
    }

    /**
     * 权限不通过的处理
     */
    public  class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request,
                             HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            response.setStatus(500);
            //将 登录失败 信息打包成json格式返回
            response.setContentType("application/json;charset=UTF-8");
            if ("Full authentication is required to access this resource".equals(authException.getMessage())){
                response.getWriter().write(objectMapper.writeValueAsString(Result.newFailure("当前未登陆或未有该权限访问")));
                return;
            }
            if ("坏的凭证".equals(authException.getMessage())){
                response.getWriter().write(objectMapper.writeValueAsString(Result.newFailure("账号或密码出错")));
                return;
            }
            logger.error(authException.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(Result.newFailure("出现未知异常")));
        }
    }



}
