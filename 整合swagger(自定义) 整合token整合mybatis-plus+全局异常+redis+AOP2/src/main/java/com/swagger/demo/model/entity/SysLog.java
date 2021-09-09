package com.swagger.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zrc
 * @since 2021-09-07
 */
@Data
@TableName(value = "syslog")
@Accessors(chain = true)
public class SysLog {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;//id

    @TableField("operationUser")
    private String operationUser;//操作人

    @TableField("path")
    private String path;//请求路径

    @TableField("time")
    private String time;//方法执行时间

    @TableField("parameter")
    private String parameter;//方法入参

    @TableField("title")
    private String title;//操作方法

    @TableField("action")
    private String action;//方法描述

    @TableField("sysType")
    private Integer sysType;//系统类型

    @TableField("opType")
    private Integer opType;//操作类型

    public SysLog(String operationUser, String path, String time,
                      String parameter, String title, String action, Integer sysType, Integer opType) {
        super();
        this.operationUser = operationUser;
        this.path = path;
        this.time = time;
        this.parameter = parameter;
        this.title = title;
        this.action = action;
        this.sysType = sysType;
        this.opType = opType;
    }

}
