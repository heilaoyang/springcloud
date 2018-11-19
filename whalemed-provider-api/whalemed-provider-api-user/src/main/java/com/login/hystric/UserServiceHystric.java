package com.login.hystric;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.demo.ApiCode;
import com.common.demo.LoginUser;
import com.common.demo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.login.service.UserService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserServiceHystric implements FallbackFactory<UserService> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    ObjectMapper mapper=new ObjectMapper();
    public static ThreadLocal<Throwable> threadLocal=new ThreadLocal<Throwable>();
    @Override
    public UserService create(Throwable cause) {
        logger.info("the provider error is: {}", cause.getMessage());
        threadLocal.set(cause);
        return new UserService() {
            @Override
            public Result login(LoginUser user) {
                return errorHandel();
            }

            @Override
            public Result loginByPhone(String phone, String code, HttpServletRequest request) {
                return errorHandel();
            }

            @Override
            public Result sendPhoneCode(String phone) {
                return errorHandel();
            }

            @Override
            public Result getUserDetails() {
                return errorHandel();
            }

            private Result errorHandel(){
                String errorMessage=threadLocal.get().getMessage();
                Result result=null;
                if (errorMessage.contains("code")&&errorMessage.contains("message")){
                    try {
                        return Result.newFailureFromJson(errorMessage);
                    } catch (Exception e) {
                        logger.error("断路器在对返回结果反序列化时异常");
                        return Result.newFailure(ApiCode.Public_Error);
                    }
                    finally {
                        threadLocal.remove();
                    }
                }
                return Result.newFailure("服务器加载出错，请重新刷新");
            }
        };

    }
}
