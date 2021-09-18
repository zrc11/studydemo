package com.swagger.demo.listener;

import com.swagger.demo.service.impl.ScoreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName JobListener任务监听器
 * @Description TODO
 * @Author zrc
 * @Date 12:25
 * @Version 1.0
 **/
@Component
public class JobListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobListener.class);
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("任务开始之前 " + jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("任务状态 : {}", jobExecution.getStatus());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("任务完成");
            threadPoolTaskExecutor.destroy();
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("任务失败");
        }
        log.info("任务花费时间 : {}ms" , (System.currentTimeMillis() - startTime));
    }
}