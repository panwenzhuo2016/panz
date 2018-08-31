package com.pwz.util;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/17 12:49
 **/
public class HttpUtil {
    public static String getResult(String u) throws Exception {
        String path = u;
        String reqUrl = path + "";
        URL url = new URL(reqUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Charset", "utf-8");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.connect();
        String msg = "";
        if (conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"GBK"));
            msg = in.readLine();
            in.close();
        }
        conn.disconnect();
        return msg;
    }

    public static String  sendPostForm( String path ) throws Exception{

        String parm = "username=234&password=456";
        byte[] data = parm.getBytes();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(RequestMethod.POST.name());
        conn.setDoOutput(true);
        conn.setRequestProperty("Connection", "close");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        String msg="";
        if(conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
             msg = in.readLine();
            System.out.println("msg: " + msg);
            in.close();
        }
        conn.disconnect();
        return msg;
    }

}
