package com.login.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 日志配置
 * @Author: hj
 * @Date: 10:40 2017/11/10
 */
@Aspect
@Component
public class LogAopAction {

    final Logger logger = LoggerFactory.getLogger(LogAopAction.class);

    @Pointcut("execution(public * com.login.controller..*(..))")
    private void pointCutMethod(){}

    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String uri = request.getRequestURI();  //URI
        String ip = request.getRemoteHost();   //IP
        String method = request.getMethod();   //请求方法类型

        Object[] args = joinPoint.getArgs();   //参数列表
        List<String> params = new ArrayList<String>();
        if (args==null&&args.length>0){
            for(Object arg : args){
                if(arg instanceof MultipartFile){
                    MultipartFile file = (MultipartFile)arg;
                    params.add("FILE:"+file.getOriginalFilename());
                    continue;
                }
                params.add(arg.toString());
            }
        }
        logger.info(">>>接口:[{}]收到来自IP:[{}]的[{}]请求,参数为:{}",uri,ip,method,params);
    }

    @AfterReturning(value = "pointCutMethod()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String uri = request.getRequestURI();  //URI
        String method = request.getMethod();   //请求类型
        String ip = request.getRemoteHost();   //IP

        logger.info("<<<接口:[{}][{}]的请求,返回给[{}]数据:[{}]",uri,method,ip,result);
    }

}