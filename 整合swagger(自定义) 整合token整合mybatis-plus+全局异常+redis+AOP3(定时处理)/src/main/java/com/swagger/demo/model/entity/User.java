package com.swagger.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
@Data
//@EqualsAndHashCode(callSuper = true)
//@Accessors(chain = true)
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @TableField("uid")
    private String uid;

    @TableField("userName")
    private String userName;

    @TableField("password")
    private String password;

}

