package com.swagger.demo.config;

/**
 * @ClassName ThreadExecutorConfig
 * @Description TODO
 * @Author zrc
 * @Date 10:15
 * @Version 1.0
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 线程池配置
 */
@Configuration
@EnableAsync//开启异步调用
public class ThreadExecutorConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /** 核心线程数 */
    private int corePoolSize = 10;
    /** 最大线程数 */
    private int maxPoolSize = 10;
    /** 队列数 */
    private int queueCapacity = 10;

    @Bean
    public Executor threadPoolTaskExecutor(){
        logger.info("创建线程池");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //设置队列数
        executor.setQueueCapacity(queueCapacity);
        //设置线程名称前缀
        executor.setThreadNamePrefix("threadPoolTaskExecutor-》》》");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // 设置拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor myThreadPool() {
        // 设置核心线程数
        int corePoolSize = 5;
        // 设置最大线程数
        int maxPoolSize = 5;
        // 设置工作队列大小
        int queueCapacity = 2000;
        // 最大存活时间
        long keepAliveTime = 30;
        // 设置线程名称前缀
        String threadNamePrefix = "myThreadPool-->";
        // 设置自定义拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException("自定义的RejectedExecutionHandler");
            }
        };
        // 自定义线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private int i = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(threadNamePrefix + i);
                i++;
                return thread;
            }
        };
        // 初始化线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueCapacity),
                threadFactory, rejectedExecutionHandler);
        return threadPoolExecutor;
    }

    @Bean
    public Executor myThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new MyThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        // 设置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(5);
        // 设置工作队列大小
        threadPoolTaskExecutor.setQueueCapacity(2000);
        // 设置线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("myThreadPoolTaskExecutor-->");
        // 设置拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
