package com.swagger.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swagger.demo.config.BaseErrorEnum;
import com.swagger.demo.config.BaseException;
import com.swagger.demo.mapper.CodeMapper;
import com.swagger.demo.mapper.UserMapper;
import com.swagger.demo.model.bo.UserDetailBO;
import com.swagger.demo.model.bo.UserLoginBO;
import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.User;
import com.swagger.demo.service.CodeService;
import com.swagger.demo.service.IUserService;
import com.swagger.demo.utils.RedisUtils;
import com.swagger.demo.utils.TokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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
public class CodeServiceImpl implements CodeService {

    /**
     * 静态变量：系统日志
     */
    private static final Log logger = LogFactory.getLog(CodeServiceImpl.class);

    @Resource
    private CodeMapper codeMapper;


    @Override
    public List<Code> getCodes() {
        logger.info("开始查询");
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        return codeMapper.selectList(queryWrapper);
    }

    @Override
    public String getUserNumber() {
        logger.info("开始查询用户人数");
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("codename","userNumber");
        return codeMapper.selectOne(queryWrapper).getNumber();
    }

    @Override
    public String updateUserNumber() {
        logger.info("开始修改用户人数");
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("codename","userNumber");
        Code code = codeMapper.selectOne(queryWrapper);
        Integer integer = Integer.parseInt(code.getNumber())+1;
        code.setNumber(integer.toString());
        codeMapper.update(code,queryWrapper);
        return "修改成功";
    }
}
