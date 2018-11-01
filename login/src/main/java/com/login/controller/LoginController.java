package com.login.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.demo.ApiCode;
import com.common.demo.LoginUser;
import com.common.demo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.login.Service.TbMasDoctorService;
import com.login.dao.TbMasDoctorMapper;
import com.login.redis.RedisHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


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
public class LoginController {


    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    TbMasDoctorService doctorService;
    @Autowired
    RedisHandle redisHandle;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/kaptcha/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    @RequestMapping("/Error")
    public Result error(){
        return Result.newFailure(ApiCode.NO_AUTHORITY);
    }

    @RequestMapping(value = "/authentication/logins",produces = "application/json;charset=UTF-8")
    public Result login(@RequestBody LoginUser user){
        String token=null;
        try {
            token = doctorService.login(user.getUsername(),user.getPassword());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return Result.newFailure("json格式转化出错");
        }
        return token==null?Result.newFailure(ApiCode.Public_Error):Result.newSuccess(token,ApiCode.Public_SUCCESSFULLY);
    }

//    @RequestMapping("/regist")
//    public String regist(@Validated User user, String code, HttpServletRequest httpServletRequest, Model model){
//        String o = redisHandle.get(httpServletRequest.getSession().getId()).toString();
//        String pwd = user.getPwd();
//        if (!StringUtils.equals(redisHandle.get(httpServletRequest.getSession().getId()).toString(),code)||StringUtils.isEmpty(code)){
//            model.addAttribute("code","验证码错误,请重新输入");
//            return "/register";
//        }
//        if (null!=userService.selectOne(new EntityWrapper<User>().eq("Account",user.getAccount()))){
//            model.addAttribute("Account","账户已存在,请重新输入");
//            return "/register";
//        }
//        user.setPwd(new SimpleHash("MD5", user.getPwd(),ByteSource.Util.bytes( user.getAccount())).toString());
//        if (userService.insertAllColumn(user)){
//            login(user.getAccount(),pwd);
//            return "/index";
//        }
//        else
//        {
//            model.addAttribute("message","请正常填写表单信息");
//            return "/register";
//        }
//    }






}

