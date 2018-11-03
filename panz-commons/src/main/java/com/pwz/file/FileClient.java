package com.pwz.file;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class FileClient {
    /**
	 * 将文件提交至文件服务器
	 * @param file 文件对象
	 * @return FileStatus 上传结果
	 */
	public static String postFile(File file) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = null;
		try {
			HttpPost httpPost = new HttpPost("POST");
			MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
			mEntityBuilder.addBinaryBody("file", file);
			httpPost.setEntity(mEntityBuilder.build());
			response = httpclient.execute(httpPost);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				result =EntityUtils.toString(resEntity);
				// 消耗掉response
				EntityUtils.consume(resEntity);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			HttpClientUtils.closeQuietly(httpclient);
			HttpClientUtils.closeQuietly(response);
		}
		return result;
	}



}
//
//    /**
//     * 上传文件
//     * @param fileRequest
//     */
//    @RequestMapping("/postFile")
//    public void postFile(HttpServletRequest request, HttpServletResponse response) {
//        String result = "error";
//        Map<String, Object> map = new HashMap<String, Object>();
//        // 创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
//                .getServletContext());
//        // 判断 request 是否有文件上传,即多部分请求
//        if (multipartResolver.isMultipart(request)) {
//            // 转换成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            // 取得request中的所有文件名
//            Iterator<String> iter = multiRequest.getFileNames();
//            while (iter.hasNext()) {
//                // 取得上传文件
//                MultipartFile multipartFile = multiRequest.getFile(iter.next());
//                if (multipartFile != null) {
//                    // 取得当前上传文件的文件名称
//                    String fileName = multipartFile.getOriginalFilename();
//                    if (fileName.trim() != null && fileName.trim().length() > 0) {
//                        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
//                        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//                        File tempFile = fi.getStoreLocation();
//                        // 拿到文件，存储
//						...
//                        result = "success";
//                    }
//                }
//            }
//        }
//    }
//	map.put("result", result);
//            this.renderJson(response, map);
//            }
//
//    /**
//     * 输出文本内容到页面上
//     * @param text 文本内容
//     * @param contentType 返回类型
//     */
//    private void render(HttpServletResponse response, String text, String contentType) {
//        if (response != null) {
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//            response.setContentType(contentType);
//            PrintWriter pWriter = null;
//            try {
//                pWriter = response.getWriter();
//                pWriter.write(text);
//                pWriter.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (pWriter != null) {
//                    pWriter.close();
//                }
//            }
//        }
//    }
//
//    /**
//     * 输出普通文本到页面
//     * @param text 普通文本
//     */
//    protected void renderText(HttpServletResponse response, String text) {
//        this.render(response, text, "text/plain;charset=UTF-8");
//    }
//
//    /**
//     * 输出Html格式的文本到页面
//     * @param text Html文本内容
//     */
//    protected void renderHtml(HttpServletResponse response, String html) {
//        this.render(response, html, "text/html;charset=UTF-8");
//    }
//
//    /**
//     * 输出Json格式的文本到页面
//     * @param json Json文本内容
//     */
//    protected void renderJson(HttpServletResponse response, String json) {
//        this.render(response, json, "text/json;charset=UTF-8");
//    }
//
//    /**
//     * 输出Json格式的文本到页面
//     * @param obj 待转换对象
//     */
//    protected void renderJson(HttpServletResponse response, Object obj) {
//        Gson gson = new Gson();
//        String json = gson.toJson(obj);
//        this.renderJson(response, json);
//    }
//
//    /**
//     * 输出XML格式的文本到页面
//     * @param xml XML文本内容
//     */
//    protected void renderXML(HttpServletResponse response, String xml) {
//        this.render(response, xml, "text/xml;charset=UTF-8");
//    }
//
//    /**
//     * 输出结果“success”到页面
//     */
//    protected void renderSuccess(HttpServletResponse response) {
//        this.renderText(response, SUCCESS);
//    }
//
//    /**
//     * 输出结果“error”到页面
//     */
//    protected void renderError(HttpServletResponse response) {
//        this.renderText(response, ERROR);
//    }