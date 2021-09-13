package com.swagger.demo.task;

import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.SysLog;
import com.swagger.demo.model.entity.User;
import com.swagger.demo.service.CodeService;
import com.swagger.demo.service.ISyslogService;
import com.swagger.demo.utils.LogQueue;
import com.swagger.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName 启动时就从数据库查询热点数据存放到redis并定时更新
 * @Description TODO
 * @Author zrc
 * @Date 17:07
 * @Version 1.0
 **/

@Component
public class GetRedisData {

    @Resource
    CodeService codeService;

    @Resource
    RedisUtils redisUtils;

    public static Map<String, String> codes = new HashMap<String, String>();

    public void getData(){
        System.out.println("开启刷新热点数据");
        //系统启动中。。。从数据库获取值
        List<Code> codeList = codeService.getCodes();
        for (Code code : codeList) {
            codes.put(code.getCodename() , code.getNumber());
            //存入redis，以便于后面都从redis获取值，而不是每次从数据库直接查询
            redisUtils.getAndSet(code.getCodename(),code.getNumber());
        }
    }

    @PreDestroy
    public void destroy() {
        //系统运行结束
    }

    @Scheduled(cron = "0/10 0/1 * * * ? ")
    public void getDataSchedul() {
        //每隔10秒执行一次
        getData();
    }

}
