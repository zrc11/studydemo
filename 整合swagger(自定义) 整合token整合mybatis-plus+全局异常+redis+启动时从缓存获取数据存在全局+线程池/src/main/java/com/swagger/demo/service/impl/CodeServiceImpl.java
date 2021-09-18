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
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
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
    public Future<Code> testThreadReturn() {
        logger.info("接口开始调用");
        Code code = new Code();
        code.setNumber("测试codename");
        System.out.println(Thread.currentThread().getName());
        logger.info("业务完成");
        return new AsyncResult<>(code);
    }
}
