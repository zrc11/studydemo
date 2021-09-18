package com.swagger.demo.job;

import com.swagger.demo.config.SpringBatchConfiguration;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName MyJob1
 * @Description 我的任务1
 * @Author zrc
 * @Date 14:52
 * @Version 1.0
 **/
@Component
public class MyJob2 extends QuartzJobBean {

    @Resource
    private SpringBatchConfiguration springBatchConfiguration;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务2开始执行了" + new Date());
//        springBatchConfiguration.batchlJob1();
    }
}
