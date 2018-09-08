package com.pwz.context;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:18
 **/
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext{
    @Override
    public <T> T getBean(String person, Class<T> requiredType) {
        return getBeanFactory().getBean(person,requiredType);
    }
}
