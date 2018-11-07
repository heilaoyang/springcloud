package com.login.Service;

import com.baomidou.mybatisplus.service.IService;
import com.common.demo.TbMasPatient;
import com.login.demo.SecurityUser;
import org.codehaus.jackson.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-10-29
 */
public interface TbMasDoctorService extends IService<TbMasPatient> {
    SecurityUser register(SecurityUser userToAdd);
    String login(String username, String password) throws JsonProcessingException;
    String refresh();
    String loginByPhone(String phone, HttpServletRequest request);
}
