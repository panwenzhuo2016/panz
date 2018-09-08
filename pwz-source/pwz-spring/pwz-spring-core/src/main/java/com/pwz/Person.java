package com.pwz;

import com.pwz.aop.Log;
import org.springframework.stereotype.Component;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/7 22:10
 **/
@Component
public class Person {
    private String name;
    private String age;

    @Log(operationType = "add操作:", operationName = "添加用户")
    public String sdf(String a) {
        System.out.println(name + age + a);
        System.out.println(5/0);
        return a+age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
