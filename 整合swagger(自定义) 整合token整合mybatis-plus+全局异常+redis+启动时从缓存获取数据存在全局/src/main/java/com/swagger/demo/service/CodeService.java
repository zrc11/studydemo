package com.swagger.demo.service;


import com.swagger.demo.model.entity.Code;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 登录
     * @param username
     * @param password
     * @return
     */
    List<Code> getCodes();

}
