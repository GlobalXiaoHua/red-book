package com.lateautumncoder.aspect;

import com.lateautumncoder.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by 40973 ON 2024-10-18 18:07
 */
@Aspect
@Slf4j
public class ApiOperationLogAspect {

    @Pointcut("@annotation(com.lateautumncoder.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }


    @Around(value = "apiOperationLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 请求开始时间
        long startTime = System.currentTimeMillis();
        // 获取被请求的类和方法
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String argsJsonStr = Arrays.stream(proceedingJoinPoint.getArgs()).map(JsonUtils::toJsonString).collect(Collectors.joining(","));
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String description = methodSignature.getMethod().getAnnotation(ApiOperationLog.class).description();
        log.info("====== 请求开始: 【{}】, 入参: {}, 请求类: {}, 请求方法: {} ====== ", description, argsJsonStr, className, methodName);
        Object proceed = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;
        log.info("====== 请求结束: 【{}】, 出参: {}, 请求方法: {}, 总耗时: {} ====== ", description, JsonUtils.toJsonString(proceed), methodName, endTime);
        return proceed;
    }


}
