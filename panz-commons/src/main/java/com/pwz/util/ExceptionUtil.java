package com.pwz.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/1 13:17
 **/
public class ExceptionUtil {
    public static String getContent(Exception e){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        e.printStackTrace(ps);
        String content = new String(bos.toByteArray());
        return content;
    }
}
