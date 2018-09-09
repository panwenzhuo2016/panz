package com.pwz.log;

import com.pwz.myGenerator.Log;
import com.pwz.util.DateUtil;
import com.pwz.util.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/9 18:34
 **/
@Controller
public class LogController {

    public void a(){
        Log log = new Log("newlog1");
        try {
            String sb = read("C:\\mylog\\2018-09-09\\方法调用日志记录.log",50);
            log.info(sb);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.write2Path();
        }
    }

    @RequestMapping("/log")
    public ResponseEntity<byte[]> test(HttpServletRequest request,int n) throws Exception {
        String sb = read("C:\\mylog\\2018-09-09\\方法调用日志记录.log",n);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "logfile-"+ DateUtils.format(new Date(), DateUtil.FORMATTER_OF_DATE_AND_TIME));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(sb.getBytes(), headers, HttpStatus.CREATED);
    }


    private String read(String file, long numRead) {
        StringBuffer sb = new StringBuffer();
        //调用读取方法，定义文件以及读取行数
        List<String> list = readLastNLine(new File(file), numRead);
        if (list != null) {
            for (String str : list) {
                sb.append(str).append("\n");
            }
        }
        return sb.toString();
    }

    private Double valueOf(String f) {
        try {
            return Double.valueOf(f);
        } catch (Exception e) {
            return Double.valueOf(0L);
        }
    }

    /**
     * 读取文件最后N行
     * <p>
     * 根据换行符判断当前的行数，
     * 使用统计来判断当前读取第N行
     * <p>
     * PS:输出的List是倒叙，需要对List反转输出
     *
     * @param file    待文件
     * @param numRead 读取的行数
     * @return List<String>
     */
    public static List<String> readLastNLine(File file, long numRead) {
        // 定义结果集
        List<String> result = new ArrayList<String>();
        //行数统计
        long count = 0;

        // 排除不可读状态
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }

        // 使用随机读取
        RandomAccessFile fileRead = null;
        try {
            //使用读模式
            fileRead = new RandomAccessFile(file, "r");
            //读取文件长度
            long length = fileRead.length();
            //如果是0，代表是空文件，直接返回空结果
            if (length == 0L) {
                return result;
            } else {
                //初始化游标
                long pos = length - 1;
                while (pos > 0) {
                    pos--;
                    //开始读取
                    fileRead.seek(pos);
                    //如果读取到\n代表是读取到一行
                    if (fileRead.readByte() == '\n') {
                        //使用readLine获取当前行
                        String line = new String(fileRead.readLine().getBytes("ISO-8859-1"), "utf-8");
                        //保存结果
                        result.add(line);

                        //打印当前行
                        //System.out.println(line);

                        //行数统计，如果到达了numRead指定的行数，就跳出循环
                        count++;
                        if (count == numRead) {
                            break;
                        }
                    }
                }
                if (pos == 0) {
                    fileRead.seek(0);
                    result.add(fileRead.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileRead != null) {
                try {
                    //关闭资源
                    fileRead.close();
                } catch (Exception e) {
                }
            }
        }
        Collections.reverse(result);
        return result;
    }
}
