package com.swagger.demo.service;

import com.swagger.demo.model.entity.SysLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrc
 * @since 2021-09-07
 */
public interface ISyslogService {

    void insertAll(List<SysLog> operLogs);

}
