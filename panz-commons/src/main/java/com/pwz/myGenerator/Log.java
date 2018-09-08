package com.pwz.myGenerator;


import com.pwz.util.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-6-22 9:05
 **/
public class Log {

//        private String path = "/home/weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/";//112 别的文件夹没权限
//    private String path = "/weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/";//正式
    private String path = "c:/";//测试
    private StringBuffer sb = new StringBuffer();

    public Log(String path) {
        this.path += "mylog/" + DateUtil.format(new Date(),"yyyy-MM-dd") + "/";
        File file = new File(this.path);
        if(!file.exists()){
            file.mkdirs();
        }
        this.path += path;
    }

    public  StringBuffer info(String msg){
        sb.append(msg).append("\n");
        return sb;
    }
    public  StringBuffer insert(String msg){
        sb.insert(0,msg).append("\n");
        return sb;
    }
    public  boolean write2Path(){
        if(sb.length() == 0){
            return true;
        }
        try{
            insert("打印时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n");
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path+ ".log"),true));
            writer.write("\n"+sb);
            writer.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        Log log= new Log("ddddd");
        log.info("sdfsdf");
        log.write2Path();
        log= new Log("ddddd");
        log.info("sdfsdfwerwer");
        log.write2Path();
    }

    public StringBuffer error(String s, Exception e) {
        sb.append(s +"----报错 Exception ："+ e.getMessage()).append("\n");
        return sb;
    }
}
