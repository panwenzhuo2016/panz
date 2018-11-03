package com.pwz.javasisst;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/24 0:15
 **/
public class Person {
    private String A;
    private String age;
    private String name;

    public Person(String age, String name) {
        this.age = age;
        this.name = name;
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

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }
}
