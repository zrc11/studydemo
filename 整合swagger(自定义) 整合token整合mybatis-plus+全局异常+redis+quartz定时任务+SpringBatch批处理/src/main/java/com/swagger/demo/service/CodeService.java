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
     * 获取热点数据
     * @return
     */
    List<Code> getCodes();

    /**
     * 获取用户人数
     * @return
     */
    String getUserNumber();

    /**
     * 修改用户人数
     * @return
     */
    String updateUserNumber();

}
