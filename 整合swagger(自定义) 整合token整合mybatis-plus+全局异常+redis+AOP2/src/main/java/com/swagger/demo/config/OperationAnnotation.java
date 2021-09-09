package com.swagger.demo.config;

import java.lang.annotation.*;

/**
 * 写法：
 * @Retention(RetentionPolicy.RUNTIME)
 * @Target(ElementType.METHOD)
 * @Documented
 * 自定义注解需要加的注解
 * 参数写法： 数据类型  参数名称()  default  默认值；
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationAnnotation {
    String content() default "";//内容

    int sysType() default 0;//系统类型（管理平台，App端）

    int opType() default 0;//操作类型（0登录，1增加，2删除，3修改，4查询，5查看）
    String action() default "";//功能名称
}
