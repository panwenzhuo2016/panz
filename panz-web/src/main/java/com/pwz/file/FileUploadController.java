package com.pwz.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/19 18:57
 **/
@Controller
public class FileUploadController {
    /**
     * 实现文件上传
     */
    @RequestMapping("fileUpload2")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "F:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public void processUpload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String uploadPath = "F:/test";
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        System.out.println("Come on, baby .......");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //检测是不是存在上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if(isMultipart){
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb
            factory.setSizeThreshold(1024*1024);

            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
            factory.setRepository(new File("D:\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
            upload.setFileSizeMax(50 * 1024 * 1024);

            //指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
            upload.setSizeMax(50 * 1024 * 1024);
            upload.setHeaderEncoding("UTF-8");

            List<FileItem> items = null;

            try {
                // 解析request请求
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            if(items!=null){
                //解析表单项目
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    //如果是普通表单属性
                    if (item.isFormField()) {
                        //相当于input的name属性   <input type="text" name="content">
                        String name = item.getFieldName();

                        //input的value属性
                        String value = item.getString();

                        System.out.println("属性:" + name + " 属性值:" + value);
                    }
                    //如果是上传文件
                    else {
                        //属性名
                        String fieldName = item.getFieldName();

                        //上传文件路径
                        String fileName = item.getName();
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名

                        try {
                            item.write(new File(uploadPath, fileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        response.addHeader("token", "hello");
    }


    /**
     * 实现多文件上传
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multifileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

        if (files.isEmpty()) {
            return "false";
        }

        String path = "F:/test";

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }


    @RequestMapping("download")
    public String downLoad(HttpServletResponse response){
        String filename="2.jpg";
        String filePath = "F:/test" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
