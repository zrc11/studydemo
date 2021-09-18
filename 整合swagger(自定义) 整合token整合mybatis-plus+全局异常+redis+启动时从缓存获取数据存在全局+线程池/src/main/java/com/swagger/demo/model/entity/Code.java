package com.swagger.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName(value = "code")
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @TableField("id")
    private String id;

    @TableField("codename")
    private String codename;

    @TableField("number")
    private String number;

}

