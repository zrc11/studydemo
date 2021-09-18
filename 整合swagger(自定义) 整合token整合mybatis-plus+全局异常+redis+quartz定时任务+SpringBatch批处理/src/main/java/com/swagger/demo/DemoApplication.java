package com.swagger.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//访问地址：http://localhost:8087/doc.html
//实现每周周天将分数表（score）的数据按用户相加并存储到排名表（ranking）进行排序。（此处为模拟，所以cron表达式就写成每一分钟执行一次）
@SpringBootApplication
@EnableSwagger2
@EnableBatchProcessing
//@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
