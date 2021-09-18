package com.swagger.demo.config;

import com.swagger.demo.job.MyJob1;
import com.swagger.demo.job.MyJob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QuartzConfiguration
 * @Description TODO
 * @Author zrc
 * @Date 14:55
 * @Version 1.0
 **/
@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail MyJob1JobDetail(){
        return JobBuilder.newJob(MyJob1.class)//PrintTimeJob我们的业务类
                .withIdentity("MyJob1")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("MyJob1", "Hello Quartz")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger MyJob1JobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ? *");//每30秒执行
        return TriggerBuilder.newTrigger()
                .forJob(MyJob1JobDetail())//关联上述的JobDetail
                .withIdentity("MyJob1JobTrigger")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail MyJob2JobDetail(){
        return JobBuilder.newJob(MyJob2.class)//PrintTimeJob我们的业务类
                .withIdentity("MyJob2")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("MyJob2", "Hello Quartz")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger MyJob2JobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ? *");//每30秒执行
        return TriggerBuilder.newTrigger()
                .forJob(MyJob2JobDetail())//关联上述的JobDetail
                .withIdentity("MyJob2JobTrigger")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
