package com.swagger.demo.utils;

import com.swagger.demo.model.entity.SysLog;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName LogQueue
 * @Description TODO
 * @Author zrc
 * @Date 17:04
 * @Version 1.0
 **/


@Component
public class LogQueue {

    //LinkedList实现了Queue接口，可以用LinkedList做一个队列,这里使用阻塞队列BlockingQueue
    private  volatile Queue<SysLog> dataQueue = new LinkedBlockingQueue<>();
    //添加日志信息
    public void add(SysLog sysLog) {
        dataQueue.add(sysLog);
    }
    //获取日志信息，用于插入到数据库中。
    public SysLog poll() { return dataQueue.poll(); }
}
