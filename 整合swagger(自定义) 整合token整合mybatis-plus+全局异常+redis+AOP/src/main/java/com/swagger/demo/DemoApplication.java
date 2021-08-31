package com.swagger.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//访问地址：http://localhost:8088/doc.html
//本次demo通过AOP实现每次请求接口前的token效验在切面进行，而不在业务代码内部进行（通过自定义注解并在需要效验的接口前面加上该注解）
//效果：token存储在redis中，修改拦截器实现只能存在一个token
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
