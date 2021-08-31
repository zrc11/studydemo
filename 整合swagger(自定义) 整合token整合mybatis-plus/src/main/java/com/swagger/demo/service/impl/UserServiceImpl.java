package com.swagger.demo.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swagger.demo.mapper.UserMapper;
import com.swagger.demo.model.bo.UserDetailBO;
import com.swagger.demo.model.bo.UserLoginBO;
import com.swagger.demo.model.entity.User;
import com.swagger.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swagger.demo.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserLoginBO login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            return null;
        }else{
            UserDetailBO userDetailBO = new UserDetailBO();
            BeanUtils.copyProperties(user,userDetailBO);
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUserDetailBO(userDetailBO);
            //包装token
            String token= TokenUtils.sign(user);
            userLoginBO.setToken(token);
            return userLoginBO;
        }
    }
}
