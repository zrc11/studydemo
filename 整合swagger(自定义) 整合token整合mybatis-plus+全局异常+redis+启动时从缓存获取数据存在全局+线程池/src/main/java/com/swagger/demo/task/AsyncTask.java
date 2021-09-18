package com.swagger.demo.task;

import com.swagger.demo.model.entity.Product;
import com.swagger.demo.model.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @ClassName AsyncTask
 * @Description TODO
 * @Author zrc
 * @Date 15:43
 * @Version 1.0
 **/
@Component
public class AsyncTask {

    //模拟异步方法1
    @Async
    public Future<List<User>> asyncGetData1() throws InterruptedException {
        System.out.println("开始向数据库查询user集合");
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserName("用户1");
        userList.add(user);
        Thread.sleep(1000);
        System.out.println("结束向数据库查询user集合");
        return new AsyncResult<>(userList);
    }

    //模拟异步方法2
    @Async
    public Future<List<Product>> asyncGetData2() throws InterruptedException {
        System.out.println("开始向数据库查询product集合");
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductName("产品1");
        productList.add(product);
        Thread.sleep(3000);
        System.out.println("结束向数据库查询product集合");
        return new AsyncResult<>(productList);
    }
}
