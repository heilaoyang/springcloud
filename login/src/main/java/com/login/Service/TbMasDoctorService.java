package com.login.Service;

import com.common.demo.TbMasDoctor;
import com.baomidou.mybatisplus.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.login.demo.SecurityUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-10-29
 */
public interface TbMasDoctorService extends IService<TbMasDoctor> {
    SecurityUser register(SecurityUser userToAdd);
    String login(String username, String password) throws JsonProcessingException;
    String refresh();
}
