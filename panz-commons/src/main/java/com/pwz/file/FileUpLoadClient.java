package com.pwz.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Client端<br><br>
 * 功能说明：文件上传（断点）传输
 * 
 * @author      CSDN:seesun2012
 * @CreateDate  2017年08月18日 
 * @Override    2017年11月07日
 * @version     1.1
 */
public class FileUpLoadClient extends Socket{


    private Logger logger = LoggerFactory.getLogger("oaLogger");

    private Socket client;              //  Socket-客户端
    private static long status = 0;     //  进度条
    private boolean quit = false;       //退出


    /**
     * 构造器
     * 
     * @param ip    服务端IP地址
     * @param report    服务端开放的端口    
     * @throws UnknownHostException
     * @throws IOException
     */
    public FileUpLoadClient(String ip, Integer report) throws UnknownHostException, IOException {
        super(ip, report);
        this.client = this;
        if (client.getLocalPort()>0) {
            System.out.println("Cliect[port:" + client.getLocalPort() + "] 成功连接服务端");
        }else{
            System.out.println("服务器连接失败");
        }
    }


    public int sendFile(String filePath) {

        DataOutputStream dos = null;    //  上传服务器：输出流
        DataInputStream dis = null;     //  获取服务器：输入流
        Long serverLength = -1l;        //  存储在服务器的文件长度，默认-1
        FileInputStream fis = null;     //  读取文件：输入流

        //  获取：上传文件
        File file = new File(filePath);

        //  ==================== 节点：文件是否存在 ==================== 
        if (file.exists()) {

            //  发送：文件名称、文件长度
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e2) {
                logger.error("Socket客户端：1.读取输出流发生错误");
                e2.printStackTrace();
            }
            try {
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
            } catch (IOException e2) {
                logger.error("Socket客户端：2.向服务器发送文件名、长度发生错误");
                e2.printStackTrace();
            }

            //  获取：已上传文件长度
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e2) {
                logger.error("Socket客户端：3.向服务器发送文件名、长度发生错误");
                e2.printStackTrace();
            }
            while(serverLength==-1){
                 try {
                    serverLength = dis.readLong();
                } catch (IOException e) {
                    logger.error("Socket客户端：4.读取服务端长度发送错误");
                    e.printStackTrace();
                }
            }

            //  读取：需要上传的文件
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e2) {
                logger.error("Socket客户端：5.读取本地需要上传的文件失败，请确认文件是否存在");
                e2.printStackTrace();
            }
            //  发送：向服务器传输输入流
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e2) {
                logger.error("Socket客户端：6.向服务器传输输入流发生错误");
                e2.printStackTrace();
            }


            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 1024;
            long progress = serverLength;

            //  设置游标：文件读取的位置
            if (serverLength==-1l) {
                serverLength = 0l;
            }
            try {
                fis.skip(serverLength);
            } catch (IOException e1) {
                logger.error("Socket客户端：7.设置游标位置发生错误，请确认文件流是否被篡改");
                e1.printStackTrace();
            }

            try {
                while (((length = fis.read(bytes, 0, bytes.length)) != -1) && quit != true) {
                    dos.write(bytes, 0, length);
                    dos.flush();
                    progress += length;
                    status =  (100 * progress / file.length());
                }
            } catch (IOException e) {
                logger.error("Socket客户端：8.设置游标位置发生错误，请确认文件流是否被篡改");
                e.printStackTrace();
            }finally {
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException e1) {
                        logger.error("Socket客户端：9.关闭读取的输入流异常");
                        e1.printStackTrace();
                    }
                if (dos != null)
                    try {
                        dos.close();
                    } catch (IOException e1) {
                        logger.error("Socket客户端：10.关闭发送的输出流异常");
                        e1.printStackTrace();
                    }
                try {
                    client.close();
                } catch (IOException e) {
                    logger.error("Socket客户端：11.关闭客户端异常");
                    e.printStackTrace();
                }
            }
            System.out.println("======== 文件传输成功 ========");

        }else{
            logger.error("Socket客户端：0.文件不存在");
            return -1;
        }

        return 1;
    }   


    /**
     *   进度条
     */
    public void statusInfo(){
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            long num = 0;
            @Override
            public void run() {
                if (status>num) {
                    System.out.println("当前进度为："+status+"%");
                    num = status;
                }
                if (status==101) {
                    System.gc();
                }
            }
        },0,100);
    }


    /**
     * 退出
     */
    public void quit() {
        this.quit  = true;
        try {
            this.close();
        } catch (IOException e) {
            System.out.println("服务器关闭发生异常，原因未知");
        }
    }

}