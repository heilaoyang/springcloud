package com.login.fiter;

import com.login.demo.SecurityUser;
import com.login.redis.RedisHandle;
import com.login.utlis.JsonToObject;
import com.login.utlis.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    RedisHandle redisHandle;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            String token = (String) redisHandle.get(tokenHead + username);
            if (!StringUtils.equals(token,authHeader) || jwtTokenUtil.isTokenExpired(authToken)) {
                chain.doFilter(request, response);
                return;
            }
            String str = (String) redisHandle.get(token);
            SecurityUser user = null;
            try {
                user = JsonToObject.parsetoSecurityUser(str);
            } catch (ParseException e) {
                logger.error("json用户信息对象转化出错" + e.getMessage() + " json对象:" + str);
                e.printStackTrace();
            }
            logger.info("checking authentication " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtTokenUtil.validateToken(authToken, user)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    final String newtoken = tokenHead + jwtTokenUtil.generateToken(user);
//                    redisHandle.set(tokenHead + user.getUsername(), token, expiration);
//                    if (!StringUtils.isEmpty((String) redisHandle.get(authHeader))) {
//                        redisHandle.remove(authHeader);
//                    }
//                    redisHandle.set(token, JSON.toJSONString(user), expiration);
                }
            }
        }
        chain.doFilter(request, response);
    }

}

