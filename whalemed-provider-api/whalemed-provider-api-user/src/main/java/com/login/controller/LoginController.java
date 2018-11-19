package com.login.controller;

import com.common.demo.LoginUser;
import com.common.demo.Result;
import com.login.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "使用账户密码登录",notes = "有效时长7天，往后请求头需携带token密令，head键名Authorization")
    @ApiImplicitParam(name = "user", value = "用户账号或手机号及密码，参数名username及password", required = true, dataType = "LoginUser", paramType = "json")
    @PostMapping(value = "/authentication/logins",produces = "application/json;charset=UTF-8")
    public Result login(@RequestBody LoginUser user){
        Result login = userService.login(user);
        return login;
    }

}
