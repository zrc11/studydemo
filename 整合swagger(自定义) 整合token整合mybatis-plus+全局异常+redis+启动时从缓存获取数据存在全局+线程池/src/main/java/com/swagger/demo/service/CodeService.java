package com.swagger.demo.service;


import com.swagger.demo.model.entity.Code;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
public interface CodeService {

    /**
     * 获取code
     * @return
     */
    List<Code> getCodes();

    /**
     * 测试线程返回值
     * @return
     */
    Future<Code> testThreadReturn();
}
