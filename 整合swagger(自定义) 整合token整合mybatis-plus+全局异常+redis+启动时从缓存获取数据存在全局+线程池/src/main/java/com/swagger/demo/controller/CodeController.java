package com.swagger.demo.controller;

import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.Product;
import com.swagger.demo.model.entity.User;
import com.swagger.demo.response.BaseResponse;
import com.swagger.demo.response.RespGenerator;
import com.swagger.demo.service.CodeService;
import com.swagger.demo.service.IUserService;
import com.swagger.demo.service.impl.CodeServiceImpl;
import com.swagger.demo.task.AsyncTask;
import com.swagger.demo.task.GetRedisData;
import com.swagger.demo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * UserController类
 *
 * <p>
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2021/8/25 17:51</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = "热点数据接口")
@RestController
@RequestMapping("codeController")
public class CodeController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private CodeService codeService;

    @Resource
    private AsyncTask asyncTask;

    /**
     * 静态变量：系统日志
     */
    private static final Log logger = LogFactory.getLog(CodeController.class);

    @ApiOperation(value = "获取首页热点数据")
    @PostMapping("/getHomeHotData")
    public BaseResponse<Map<String,String>> getHomeHotData() {
        //从redis中获取值
        Map<String,String> hash = new HashMap<>();
        hash.put("news",redisUtils.get("news"));
        hash.put("userNumber",redisUtils.get("userNumber"));
        hash.put("ranking",redisUtils.get("ranking"));
        //从缓存中获取值
        Map<String, String> codes = GetRedisData.codes;
        hash.put("codes",codes.toString());
        return RespGenerator.returnOK(hash);
    }

    @ApiOperation(value = "获取首页所有数据")
    @PostMapping("/getHomeData")
    public BaseResponse<Map<String,String>> getHomeData() {
        //从redis中获取值
        Map<String,String> hash = new HashMap<>();
        hash.put("codes",codeService.getCodes().toString());
        hash.put("product",codeService.getCodes().toString());
        return RespGenerator.returnOK(hash);
    }

    @ApiOperation(value = "测试线程")
    @PostMapping("/testThread")
    @Async("threadPoolTaskExecutor") // 参数为线程池配置时的方法名即对应的bean的id
    public void testThread() throws InterruptedException {
        logger.info("开始");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("执行方法");
        logger.info("结束");
    }

    @ApiOperation(value = "测试线程2")
    @PostMapping("/testThread2")
    @Async("myThreadPool") // 参数为线程池配置时的方法名即对应的bean的id
    public void testThread2() throws InterruptedException {
        logger.info("开始");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("执行方法");
        logger.info("结束");
    }

    @ApiOperation(value = "测试线程3")
    @PostMapping("/testThread3")
    @Async("myThreadPoolTaskExecutor") // 参数为线程池配置时的方法名即对应的bean的id
    public void testThread3() throws InterruptedException {
        logger.info("开始");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("执行方法");
        logger.info("结束");
    }

    @ApiOperation(value = "测试线程4")
    @PostMapping("/testThread4")
//    @Async()
    public void testThread4() throws InterruptedException {
        logger.info("开始");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("执行方法");
        logger.info("结束");
    }

    @ApiOperation(value = "测试线程返回值")
    @PostMapping("/testThreadReturn")
    public void testThreadReturn() throws ExecutionException, InterruptedException {
        //调用异步方法获取返回值
        long strat = System.currentTimeMillis();
        Future<List<User>> future1 = asyncTask.asyncGetData1();
        Future<List<Product>> future2 = asyncTask.asyncGetData2();
        //通过future的get方法获取具体的值
        List<User> userList= future1.get();
        List<Product> productList = future2.get();
        System.out.println(userList);
        System.out.println(productList);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-strat));
    }

}
