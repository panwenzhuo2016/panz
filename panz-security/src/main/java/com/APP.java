package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hasee on 2018/7/14.
 */
@SpringBootApplication
@MapperScan("com.winterchen.dao")
public class APP {
    public static void main(String[] args) {
        ClassLoader classLoader = APP.class.getClassLoader();
        Package aPackage = APP.class.getPackage();

        int dfs = 0xd1310b6;

        System.out.println(dfs);
        SpringApplication.run(APP.class, args);
    }
}
