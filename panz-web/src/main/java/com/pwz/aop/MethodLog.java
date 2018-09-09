package com.pwz.aop;

import java.lang.annotation.*;

/**
        * 记录方法调用
        */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodLog {

}
