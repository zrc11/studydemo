package com.swagger.demo.job;

import com.swagger.demo.service.CodeService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @ClassName MyJob1
 * @Description 我的任务1
 * @Author zrc
 * @Date 14:52
 * @Version 1.0
 **/
@Component
public class MyJob1 extends QuartzJobBean {

    @Resource
    private CodeService codeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取使用MyJob1的MyJob1JobDetail传过来的值
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobSays = jobDataMap.get("MyJob1").toString();
        System.out.println("MyJob1JobDetail传过来的值："+jobSays);
        codeService.updateUserNumber();
    }
}
