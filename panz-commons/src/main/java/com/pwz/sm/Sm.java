package com.pwz.sm;


/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-6-4 17:23
 **/
public class Sm {
    protected static final StringManager sm = StringManager.getManager(Sm.class);

    public static void main(String[] args) {
        System.out.println(sm.getString("dsdsfsdf"));
    }
}
