package com.pwz.aop;


import com.pwz.util.ExceptionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
public class TransactionDemo2 {

    @Pointcut(value = "execution(* *..*.*(..))")
    public void point() {

    }

//    @Before(value="point()")
//    public void before(){
//        System.out.println("transaction begin");
//    }
//
//    @AfterReturning(value = "point()")
//    public void after(){
//        System.out.println("transaction commit");
//    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        com.pwz.myGenerator.Log log = new com.pwz.myGenerator.Log("方法调用日志记录");
        long start = System.currentTimeMillis();
        log.info("日志记录--    方法： " + joinPoint.getSignature().toLongString());
        log.info("日志记录--    参数： " + Arrays.asList(joinPoint.getArgs()));
        try {
            Object proceed = joinPoint.proceed();
            log.info("日志记录--  返回值： " + proceed);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("日志记录--    异常： " + ExceptionUtil.getContent(e));
            throw e;
        }
        long end = System.currentTimeMillis();
        log.info("日志记录--调用时长： " + (end - start) + "ms");
        log.write2Path();
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "point()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        com.pwz.myGenerator.Log log = new com.pwz.myGenerator.Log("方法调用日志记录");
        log.info("日志记录--    方法： " + joinPoint.getSignature().toLongString());
        log.info("日志记录--    参数： " + Arrays.asList(joinPoint.getArgs()));
        log.info("日志记录--    异常： " + ExceptionUtil.getContent((Exception) e));
        log.write2Path();
    }

}