package com.pwz.util;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/31 22:35
 **/
public class NumUtil {
    public static String numFormat(int num ,int youNumber){
       return String.format("%0"+num+"d", youNumber);
    }
}
