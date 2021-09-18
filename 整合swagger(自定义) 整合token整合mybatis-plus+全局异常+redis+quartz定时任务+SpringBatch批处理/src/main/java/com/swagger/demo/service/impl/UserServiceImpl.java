package com.swagger.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swagger.demo.config.BaseErrorEnum;
import com.swagger.demo.config.BaseException;
import com.swagger.demo.mapper.UserMapper;
import com.swagger.demo.model.bo.UserDetailBO;
import com.swagger.demo.model.bo.UserLoginBO;
import com.swagger.demo.model.entity.User;
import com.swagger.demo.service.IUserService;
import com.swagger.demo.utils.RedisUtils;
import com.swagger.demo.utils.TokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    /**
     * 静态变量：系统日志
     */
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 什么时候被锁定
     */
    private final static Integer LOCKEDNUMBER = 3;

    @Override
    public UserLoginBO login(String username, String password, HttpServletRequest request) {

        //判断该账户是否被锁定
        String errorNumber;
        if(redisUtils.get("errorNumber")!=null){
            errorNumber = redisUtils.get("errorNumber");
        }else{
            errorNumber = "0";
        }
        if(Integer.parseInt(errorNumber)>=LOCKEDNUMBER){
            logger.info(username + "账户已被锁定");
            throw new BaseException(BaseErrorEnum.USER_NAME_LOCK);
        }
        //查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            logger.info(username + "用户登录失败");
            throw new BaseException(BaseErrorEnum.USER_NOT_EXISTS);
        }else if (!Objects.equals(user.getPassword(), password)){
            //密码错误
            Integer result=Integer.parseInt(errorNumber)+1;
            errorNumber = result.toString();
            if(redisUtils.get("errorNumber")!=null){
                redisUtils.getAndSet("errorNumber",errorNumber);
            }else{
                redisUtils.set("errorNumber",errorNumber);
            }
            throw new BaseException(BaseErrorEnum.PASSWORD_ERROR);
        } else{
            UserDetailBO userDetailBO = new UserDetailBO();
            BeanUtils.copyProperties(user,userDetailBO);
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUserDetailBO(userDetailBO);
            //包装token
            String token= TokenUtils.sign(user);
            //token存入redis
            redisUtils.set("token",token,30, TimeUnit.MINUTES);
            userLoginBO.setToken(token);
            logger.info(username + "用户登录成功");
            HttpSession session = request.getSession();
            //将数据存储到session中
            session.setAttribute("userName", username);
            return userLoginBO;
        }
    }
}
