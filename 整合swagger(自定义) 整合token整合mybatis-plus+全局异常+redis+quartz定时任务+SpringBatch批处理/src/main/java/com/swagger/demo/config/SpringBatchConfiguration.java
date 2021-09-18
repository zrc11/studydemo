package com.swagger.demo.config;

import com.google.common.collect.Maps;
import com.swagger.demo.batch.TestProcessor;
import com.swagger.demo.batch.TestReader;
import com.swagger.demo.batch.TestWriter;
import com.swagger.demo.job.MyJob2;
import com.swagger.demo.listener.JobListener;
import com.swagger.demo.model.entity.Product;
import com.swagger.demo.model.entity.Score;
import org.quartz.JobDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @ClassName QuartzConfiguration
 * @Description TODO
 * @Author zrc
 * @Date 14:55
 * @Version 1.0
 **/
@Configuration
public class SpringBatchConfiguration {

    @Resource
    private TestProcessor testProcessor;

    @Resource
    private TestReader testReader;

    @Resource
    private TestWriter testWriter;

    @Resource
    private JobListener jobListener;

    @Resource
    private JobBuilderFactory jobBuilderFactory;    //用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory;  //用于构建Step

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")//创建一个step
                .<Object, Set<Score>>chunk(10)//每次执行的次数
                .reader(testReader)//设置读处理器
                .processor(testProcessor)//设置处理器
                .writer(testWriter)//设置写处理器
                .build();
    }

    @Bean
    public Job batchlJob1(){
        return jobBuilderFactory.get("batchlJob1")//创建一个batch-job
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)//设置任务监听
                .flow(step1())
                .end()
                .build();
    }


}
