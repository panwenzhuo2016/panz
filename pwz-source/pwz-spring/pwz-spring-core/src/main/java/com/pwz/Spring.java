package com.pwz;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:08
 **/
public class Spring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        System.out.println(applicationContext);
//        Environment environment = applicationContext.getEnvironment();
//        System.out.println(environment.getClass());
//        System.out.println(applicationContext.getApplicationName());
//        System.out.println(applicationContext.getAutowireCapableBeanFactory());
//        System.out.println(applicationContext.getDisplayName());
//        System.out.println(applicationContext.getId());
//        System.out.println(applicationContext.getParent());
//        System.out.println(applicationContext.getStartupDate());
        Person person = applicationContext.getBean("person",Person.class);
//        System.out.println(person);
        person.sdf("3434");

        System.out.println("ddd");
    }
}
