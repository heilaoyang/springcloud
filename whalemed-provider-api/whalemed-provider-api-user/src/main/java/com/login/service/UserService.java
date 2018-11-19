package com.login.service;

import com.common.demo.LoginUser;
import com.common.demo.Result;
import com.login.hystric.UserServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "whalemed-user-server",fallbackFactory=UserServiceHystric.class)
public interface UserService {

    @PostMapping(value = "/authentication/logins",produces = "application/json;charset=UTF-8")
    public Result login(@RequestBody LoginUser user);

    @GetMapping(value = "/authentication/loginByPhone/{phone}/{code}")
    public Result loginByPhone(@PathVariable(value = "phone") String phone, @PathVariable(value = "code") String code, HttpServletRequest request);

    @GetMapping("/sendPhoneCode/{phone}")
    public Result sendPhoneCode(@PathVariable(value = "phone") String phone);

    @GetMapping("/user/getDetails")
    public Result getUserDetails();
}
