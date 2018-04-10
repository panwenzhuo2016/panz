package com.pwz.myclass;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.xml.ws.Endpoint;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            ApplicationContext applicationContext = event.getApplicationContext();
//            JiaoYiBeiAnWebService jiaoYiBeiAnWebService = applicationContext.getBean(JiaoYiBeiAnWebService.class);
            Endpoint.publish("http://192.168.3.14:8083/jy-fuwu/services/", null);
        }
    }
}