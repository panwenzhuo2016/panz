package com.pwz.context;

import com.pwz.Person;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:12
 **/
public interface BeanFactory {
    <T> T getBean(String person, Class<T> requiredType);
}
