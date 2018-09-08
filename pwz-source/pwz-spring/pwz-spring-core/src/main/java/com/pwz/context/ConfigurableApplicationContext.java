package com.pwz.context;


/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:31
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {
    BeanFactory getBeanFactory();
}
