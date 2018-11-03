package com.pwz.util;

import com.pwz.myGenerator.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


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

        spring.info("applicationContext ： " + applicationContext.getApplicationName() + "");
        spring.info("bean数量 ： " + allBeanNames.length + "个");
        for (String beanName : allBeanNames) {
            spring.info("bean名字 ： " + beanName);
            Object bean = applicationContext.getBean(beanName);
            Map<String, Object> inner = inner(bean);
//            spring.info("["+beanName + " 所有键值 " + inner.toString() + "]");
            spring.info("bean实体 ： " + bean.toString());
            spring.info("bean是否单例 ： " + applicationContext.isSingleton(beanName));
            spring.info("bean是否原型 ： " + applicationContext.isPrototype(beanName));
            spring.info("bean别名 ： " + Arrays.toString(applicationContext.getAliases(beanName)));
            spring.info("  ");
        }
        spring.write2Path();
        Object bean = applicationContext.getBean("IClassifyDao");
//        System.out.println(MapperProxy.class.isInstance(bean));
        Proxy proxy = (Proxy) bean;
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
//        System.out.println(invocationHandler instanceof MapperProxy);
//        System.out.println(bean instanceof IClassifyDao);

        for (String beanName : allBeanNames) {
//            spring.info("bean名字 ： " + beanName);
//            Object bean1 = applicationContext.getBean(beanName);
//            System.out.println(bean1);
        }
    }

    private Map<String, Object> inner(Object bean) {
       return getKeyAndValue(bean);
    }


    /**
     * 单个对象的所有键值
     *
     * @param obj
     *            单个对象
     *
     * @return Map<String, Object> map 所有 String键 Object值 ex：{pjzyfy=0.00,
     *         xh=01, zzyl=0.00, mc=住院患者压疮发生率, pjypfy=0.00, rs=0, pjzyts=0.00,
     *         czydm=0037, lx=921, zssl=0.00}
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if(obj == null){
            return null;
        }
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            // 设置些属性是可以访问的
            Object val;
            try {
                val = f.get(obj);
                // 得到此属性的值
//                if(isnotbase(val)){
//                    getKeyAndValue(val);
//                }
                map.put(f.getName(), val);
                // 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    private static boolean isnotbase(Object val) {
        return !(val instanceof String)
                && !(val instanceof Byte)
                && !(val instanceof Short)
                && !(val instanceof Integer)
                && !(val instanceof Long)
                && !(val instanceof Float)
                && !(val instanceof Double)
                && !(val instanceof Character)
                && !(val instanceof Boolean)
                ;
    }


    /**
     * 获取属性名数组
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static DataSource getDataSource() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        return dataSource;
    }

    public static void main(String[] args) {

        Object aa = 'a';
        System.out.println(aa);
        class checkedAddre {
            public checkedAddre() {
                System.out.println("sdfsdf");
            }
        }

        new checkedAddre();
        new SpringUtil();
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
