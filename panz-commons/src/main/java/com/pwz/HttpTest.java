//package com.pwz;
//
//import com.alibaba.fastjson.JSONObject;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * 测试类
// * @author pwz
// * @create 2016-07-25
// */
//public class HttpTest {
//
//
////    @Test
//	public void sendPostJson() throws Exception{
//		String path = "http://127.0.0.1:8087/dsp/JWTProvider";
////		String path = "http://127.0.0.1:8087/dsp/rest/pdsc/bug/BugOrder.spr?loadAppBugDetail&deviceId=005b4dc8-6df2-414b-a14a-a87a5646&bugId=&registerId=557b16e754674b10b8b665b1623d6b2c";
////		String path = "http://localhost:8087/dsp/platform/authority/home.spr";
//		JSONObject obj = new JSONObject();
//		obj.put("","");
//		String jsonStr = obj.toJSONString();
//        String base64 = Base64.encode("admin:123456".getBytes());
////        String base64 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTUwNjU2MjE2Nn0.urcC7fgILUhpv-7dDqvZ2mNLr3n_DwU4Yp31Mm_cg90";
//		byte[] data = jsonStr.getBytes();
//        URL url = new URL(path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod(RequestMethod.POST.name());
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Authorization","Bearer "+base64);
//        conn.setRequestProperty("Connection", "keep-alive");
//        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
//        OutputStream outStream = conn.getOutputStream();
//        outStream.write(data);
//        outStream.flush();
//        outStream.close();
//        if(conn.getResponseCode() == 200){
//        	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        	String msg = in.readLine();
//        	System.out.println(msg);
//            in.close();
//        }
//        conn.disconnect();
//	}
//
////	@Test
//    public void sendPostForm() throws Exception{
//		String path = "http://127.0.0.1:8080/user/login";
//		String parm = "username=234&password=456";
//		byte[] data = parm.getBytes();
//        URL url = new URL(path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod(RequestMethod.POST.name());
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Connection", "close");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
//        OutputStream outStream = conn.getOutputStream();
//        outStream.write(data);
//        outStream.flush();
//        outStream.close();
//        if(conn.getResponseCode() == 200) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String msg = in.readLine();
//        	System.out.println("msg: " + msg);
//            in.close();
//        }
//        conn.disconnect();
//	}
//
////	@Test
//    public void sendGet() throws Exception{
//		String path = "http://127.0.0.1:8080/login.html";
//		String reqUrl = path + "";
//        URL url = new URL(reqUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod(RequestMethod.GET.name());
//        conn.setDoOutput(true);
//        conn.connect();
//        if (conn.getResponseCode() == 200) {
//	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        String msg = in.readLine();
//	        System.out.println(msg);
//	        in.close();
//        }
//        conn.disconnect();
//	}
//
//
//}
