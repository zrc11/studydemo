package com.swagger.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swagger.demo.model.bo.UserDetailBO;
import com.swagger.demo.model.bo.UserLoginBO;
import com.swagger.demo.model.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
public interface IUserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    UserLoginBO login(String username, String password);

}
