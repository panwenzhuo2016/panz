package com.pwz.aop;


import com.pwz.util.ExceptionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


@Aspect
@Component
public class TransactionDemo2 {

    @Pointcut(value = "execution(* com.pwz.gupiao..*.*(..))")
    public void point() {

    }

    private AtomicInteger count = new AtomicInteger(1);
    //    @Around("point()")
    @Around("@annotation(com.pwz.aop.MethodLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        count.getAndIncrement();
        com.pwz.myGenerator.Log log = new com.pwz.myGenerator.Log("方法调用日志记录");
        long start = System.currentTimeMillis();
        log.info("日志记录--    方法： " + joinPoint.getSignature().toLongString());
        log.info("日志记录--    参数： " + Arrays.asList(joinPoint.getArgs()));
        Object proceed;
        try {
            proceed = joinPoint.proceed();
            log.info("日志记录--  返回值： " + proceed);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("日志记录--    异常： " + ExceptionUtil.getContent(e));
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            log.info("日志记录--调用时长： " + (end - start) + "ms; 调用次数：" + count);
            log.write2Path();
        }
        return proceed;
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing(pointcut = "point()", throwing = "e") //定义规则，拦截所有的spring bean 代理的类的方法
    @AfterThrowing(pointcut = "@annotation(com.pwz.aop.MethodLog)", throwing = "e") //拦截带有注解的的spring bean 代理的类的方法
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        count.decrementAndGet();
        com.pwz.myGenerator.Log log = new com.pwz.myGenerator.Log("方法调用日志记录");
        log.info("日志记录--    方法： " + joinPoint.getSignature().toLongString());
        log.info("日志记录--    参数： " + Arrays.asList(joinPoint.getArgs()));
        log.info("日志记录--    异常： " + ExceptionUtil.getContent((Exception) e));
        log.write2Path();
    }

}