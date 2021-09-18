package com.swagger.demo.config;

import com.swagger.demo.mapper.SyslogMapper;
import com.swagger.demo.model.entity.SysLog;
import com.swagger.demo.utils.LogQueue;
import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SystemLogAspect
 * @Description TODO
 * @Author zrc
 * @Date SystemLogAspect  14:20
 * @Version 1.0
 **/

@Aspect
@Component
@EnableAsync
public class SystemLogAspect {
    @Resource
    private SyslogMapper logMapper;//日志 mapper

    private String requestPath = null ; // 请求地址
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String user = null; // 操作人
    private HttpServletRequest request = null;//请求

    @Resource
    LogQueue logQueue;

    /**
     * 注解的位置
     */
    @Pointcut("@annotation(com.swagger.demo.config.OperationAnnotation)")
    public void logPointCut() {}

    /**
     * @param joinPoint
     * @Description 前置通知  方法调用前触发   记录开始时间,从session中获取操作人
     */
    @Before(value="logPointCut()")
    public void before(JoinPoint joinPoint){
        startTimeMillis = System.currentTimeMillis();
    }
    /**
     * @param joinPoint
     * @Description 获取入参方法参数
     * @return
     */
    public Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if(paramValues[i] instanceof Integer || paramValues[i] instanceof String) {
                param.put(paramNames[i], paramValues[i]);
            }
        }
        return param;
    }
    /**
     * @param joinPoint
     * @Description 后置通知    方法调用后触发   记录结束时间 ,操作人 ,入参等
     */
    @After(value="logPointCut()")
    public void after(JoinPoint joinPoint) {
        request = getHttpServletRequest();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        String title;
        String action;
        Integer sysType;
        Integer opType;
        Class<?>[] clazzs;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                clazzs = method.getParameterTypes();
                if (clazzs!=null&&clazzs.length == arguments.length
                        &&method.getAnnotation(OperationAnnotation.class)!=null) {
                    request = getHttpServletRequest();
                    requestPath=request.getServletPath();
                    HttpSession session = request.getSession();
                    user = session.getAttribute("userName").toString();
                    title = method.getAnnotation(OperationAnnotation.class).content();
                    action = method.getAnnotation(OperationAnnotation.class).action();
                    sysType = method.getAnnotation(OperationAnnotation.class).sysType();
                    opType = method.getAnnotation(OperationAnnotation.class).opType();
                    endTimeMillis = System.currentTimeMillis();

                    SysLog log=new SysLog(user, requestPath,
                            (endTimeMillis-startTimeMillis)+"ms",
                            getNameAndValue(joinPoint).toString(), title, action,sysType,opType);
//                    System.out.println("增加参数："+log);
//                    logMapper.insert(log);
//                    break;
                    logQueue.add(log);
                }
            }
        }
    }
    /**
     * @Description: 获取request
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
    /**
     * @param joinPoint
     * @return 环绕通知
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }
    /**
     * @param joinPoint
     * @Description 异常通知
     */
    public void throwing(JoinPoint joinPoint) {
        System.out.println("异常通知");
    }
}