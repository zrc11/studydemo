package com.swagger.demo.service.impl;

import com.swagger.demo.model.entity.SysLog;
import com.swagger.demo.mapper.SyslogMapper;
import com.swagger.demo.service.ISyslogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrc
 * @since 2021-09-07
 */
@Service
public class SyslogServiceImpl implements ISyslogService {

    @Resource
    private SyslogMapper syslogMapper;

    @Override
    public void insertAll(List<SysLog> operLogs) {
        for (SysLog operLog : operLogs) {
            syslogMapper.insert(operLog);
        }
    }
}
