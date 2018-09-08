package com.pwz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 12:49
 **/
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static DataSource getDataSource() {
        DataSource dataSource=applicationContext.getBean(DataSource.class);
        return dataSource;
    }



}
