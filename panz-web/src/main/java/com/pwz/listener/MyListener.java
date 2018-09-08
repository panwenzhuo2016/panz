package com.pwz.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/1 13:38
 **/
@WebListener
public class MyListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
