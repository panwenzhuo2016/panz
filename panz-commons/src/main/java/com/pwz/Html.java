package com.pwz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Html {
    public static void main(String[] args) {
        String url = "http://cl.x4g.xyz/thread0806.php?fid=2";
        String keyword = "";
        String sd = getPageContent(url, "post", 5000);
        // 第一种方法
        // p.getPageContent(url, "post", 100500);//第二种方法
    }

    // 第二种方法
// 这种方法是JAVA自带的URL来抓取网站内容
    public static String getPageContent(String strUrl, String strPostRequest,
                                        int maxLength) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        try {
            URL newUrl = new URL(strUrl);
            HttpURLConnection hConnect = (HttpURLConnection) newUrl
                    .openConnection();
            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect
                        .getOutputStream());
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    hConnect.getInputStream()));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1
                    && (maxLength <= 0 || length < maxLength); length++) {
                buffer.append((char) ch);
            }
            String s = buffer.toString();
            s.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
            System.out.println(s);
            rd.close();
            hConnect.disconnect();
            return buffer.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
