package com.pwz.context;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:14
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableConfigApplicationContext{
    private BeanFactory beanFactory;
    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
