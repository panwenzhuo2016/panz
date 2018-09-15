package com.pwz.util;

import com.pwz.myGenerator.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;


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
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        Log spring = new Log("Springbean");

        spring.info("applicationContext ： "+applicationContext.getApplicationName()+ "");
        spring.info("bean数量 ： "+allBeanNames.length + "个");
        for(String beanName : allBeanNames) {
            spring.info("bean名字 ： "+beanName);
            Object bean = applicationContext.getBean(beanName);
            spring.info("bean实体 ： "+bean.toString());
            spring.info("bean是否单例 ： "+applicationContext.isSingleton(beanName));
            spring.info("bean是否原型 ： "+applicationContext.isPrototype(beanName));
            spring.info("bean别名 ： "+ Arrays.toString(applicationContext.getAliases(beanName)));
            spring.info("  " );
        }
        spring.write2Path();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static DataSource getDataSource() {
        DataSource dataSource=applicationContext.getBean(DataSource.class);
        return dataSource;
    }

    public static void main(String[] args) {
        class checkedAddre {
            public checkedAddre() {
                System.out.println("sdfsdf");
            }
        }

        new checkedAddre();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface iface = networkInterfaces.nextElement();
                System.out.println(iface);
                Enumeration<InetAddress> inetAddresses = iface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println(inetAddress);
                }

            }


        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


}
