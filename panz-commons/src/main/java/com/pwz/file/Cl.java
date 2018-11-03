package com.pwz.file;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/19 18:52
 **/
public class Cl {
   static Logger logger = LoggerFactory.getLogger(Cl.class);

    public static String post(String serverUrl, String fileParamName, File file, Map<String, String> params)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(serverUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 上传的文件
        builder.addBinaryBody(fileParamName, file);
        // 设置其他参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
        }
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpPost);
        if (null == response || response.getStatusLine() == null) {
            logger.info("Post Request For Url[{}] is not ok. Response is null", serverUrl);
            return null;
        } else if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.info("Post Request For Url[{}] is not ok. Response Status Code is {}", serverUrl,
                    response.getStatusLine().getStatusCode());
            return null;
        }
        return EntityUtils.toString(response.getEntity());
    }

    public static void main2(String[] args) {
        String url = "http://localhost:8034/fileUpload?fileName=wewewe";
        String fileUrl = "G:\\zhulong\\文档\\交接文档.txt";
        Map<String, String> map = new HashMap<String, String>();
        map.put("operatorJobNumber", "102800038");
        map.put("operatorEmail", "pengyuqian@aimsen.com1");
        map.put("realed", "1");
        map.put("resumeEducationBackgroundId", "6045");
        map.put("User", "user");
        map.put("Password", "70def5d4-8546-425b-83b2-e4e77b546f80");
        map.put("fileName", "dfgdfg");
        try {
            System.out.println(post(url, "fileName", new File(fileUrl), map));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fileUpload();
    }

    public static void fileUpload() {
        //构建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求
        HttpPost httpPost = new HttpPost("http://localhost:8034/fileUpload");
        InputStream inputStream = null;
        try {
            //要上传的文件
            File file = null; file = new File("G:\\zhulong\\文档\\交接文档.txt");
            //构建文件体
            FileBody fileBody = new FileBody(file,ContentType.MULTIPART_FORM_DATA,"aaa.txt");
            StringBody stringBody = new StringBody("12",ContentType.MULTIPART_FORM_DATA);
            HttpEntity httpEntity = MultipartEntityBuilder
                    .create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("aaa", fileBody)
                    .addPart("id",stringBody).build();
            httpPost.setEntity(httpEntity);
            //发送请求
            HttpResponse response = client.execute(httpPost);
            httpEntity = response.getEntity();
            if(httpEntity != null){
                inputStream = httpEntity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
