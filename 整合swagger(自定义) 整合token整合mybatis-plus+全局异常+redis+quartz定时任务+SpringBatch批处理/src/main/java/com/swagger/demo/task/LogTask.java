package com.swagger.demo.task;

import com.swagger.demo.model.entity.SysLog;
import com.swagger.demo.service.ISyslogService;
import com.swagger.demo.utils.LogQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LogTask
 * @Description TODO
 * @Author zrc
 * @Date 17:07
 * @Version 1.0
 **/

@Slf4j
@Component
public class LogTask {

    @Resource
    LogQueue logQueue;

    @Resource
    ISyslogService sysLogService;

    private volatile List<SysLog> operLogs = Collections.synchronizedList(new ArrayList<>());

//    @Scheduled(cron = "0/5 * * * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")//每隔一分钟执行一次
    @Scheduled(cron = "0 0 0/1 * * ? ")//每隔一小时执行一次
    public void logFixDelay(){
        System.out.println("进入");
        //获取日志信息
        while (true){
            SysLog operLog = logQueue.poll();
            if(null==operLog){
                break;
            }
            operLogs.add(operLog);
        }
        if(operLogs.size()>0){
            try{
                log.info("######################批量添加系统日志"+operLogs.size());
                sysLogService.insertAll(operLogs);
            }catch (Exception e){
                log.error("批量添加系统日志异常：",e);
                operLogs.clear();
            }
            operLogs.clear();
        }
    }
}
