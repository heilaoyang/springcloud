package com.login.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.demo.ApiCode;
import com.common.demo.LoginUser;
import com.common.demo.Result;
import com.common.demo.TbMasPatient;
import com.common.utlis.SmsUtlis;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.login.Service.TbMasDoctorService;
import com.login.demo.SecurityUser;
import com.login.redis.RedisHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-09-06
 */
@RestController
@Slf4j
@EnableAutoConfiguration
@Api(value = "登录模块接口",description = "登录,注册,验证码等接口,请求统一需携带Authorization请求头带上token令牌")
public class LoginController {


    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    TbMasDoctorService doctorService;
    @Autowired
    RedisHandle redisHandle;
    @Value("${sms.expiration}")
    private Long expiration;

    ObjectMapper map=new ObjectMapper();

    @ApiOperation(value = "获取随机图形验证码")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/kaptcha/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            redisHandle.set(principal.getUsername(),createText,60L);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);

        responseOutputStream.flush();
        responseOutputStream.close();
        doctorService.refresh();
    }

    @ApiOperation(value = "使用账户密码登录",notes = "有效时长7天，往后请求头需携带token密令，head键名Authorization")
    @ApiImplicitParam(name = "user", value = "用户账号或手机号及密码，参数名username及password", required = true, dataType = "LoginUser", paramType = "json")
    @PostMapping(value = "/authentication/logins",produces = "application/json;charset=UTF-8")
    public Result login(@RequestBody LoginUser user){
        String token;
        try {
            token = doctorService.login(user.getUsername(),user.getPassword());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return Result.newFailure("json格式转化出错");
        }
        return token==null? Result.newFailure(ApiCode.Public_Error): Result.newSuccess(token, ApiCode.Public_SUCCESSFULLY);
    }

    @ApiOperation(value = "使用手机验证码登录",notes = "有效时长7天，往后请求头需携带token密令，head键名Authorization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping(value = "/authentication/loginByPhone/{phone}/{code}")
    public Result loginByPhone(@PathVariable(value = "phone") String phone, @PathVariable(value = "code") String code, HttpServletRequest request){
        if (doctorService.selectOne(new EntityWrapper<TbMasPatient>().eq("PT_PHONE", phone))==null){
            return Result.newFailure(ApiCode.UN_REGISTERED);
        }
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
            return Result.newFailure("请检查你的手机号或验证码,请正确输入");
        }
        if (!Pattern.compile( "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(phone).matches()){
            return Result.newFailure("请输入正确的手机号");
        }
        String str =(String) redisHandle.get("loginCode" + phone);
        if(StringUtils.isEmpty(str)||!code.equals(str)){
            return Result.newFailure("验证码错误，请重新输入或获取");
        }
        String token = doctorService.loginByPhone(phone, request);
        if (token==null){
            return Result.newFailure(ApiCode.UN_REGISTERED);
        }
        redisHandle.remove("loginCode" + phone);
        return Result.newSuccess(token, ApiCode.Public_SUCCESSFULLY);
    }

    @ApiOperation(value = "发送手机验证码进行登录校验")
    @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sendPhoneCode/{phone}")
    public Result sendPhoneCode(@PathVariable(value = "phone") String phone){
        String oldcode = (String)redisHandle.get("loginCode" + phone);
        if (!StringUtils.isEmpty(oldcode)){
            return Result.newFailure("请勿短时间内重复获取验证码");
        }
        if (doctorService.selectOne(new EntityWrapper<TbMasPatient>().eq("PT_PHONE", phone))==null){
            return Result.newFailure(ApiCode.UN_REGISTERED);
        }
        Pattern p =  Pattern.compile( "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        String code;
        if (!m.matches()){
            return Result.newFailure("请输入正确的手机号");
        }
        try {
            code= SmsUtlis.sendSms(phone);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.newFailure("发送验证码失败");
        }
        redisHandle.set("loginCode"+phone,code,expiration);
        return Result.newSuccess(ApiCode.Public_SUCCESSFULLY);
    }


    @ApiOperation(value = "获取用户信息")
    @GetMapping("/user/getDetails")
    public Result getUserDetails(){
        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TbMasPatient user = doctorService.selectById(principal.getUserId());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (user!=null){
            return Result.newSuccess(user, ApiCode.Public_SUCCESSFULLY,doctorService.refresh());
        }
        return Result.newFailure(ApiCode.NOT_LOGIN_YET);
    }



}

