package com.pwz.myclass.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hasee on 2018/6/7.
 */
public class I {
    /**
     * 使用NIO读取指定文件的前1024个字节的内容。
     * @param file 指定文件名称。
     * @throws java.io.IOException IO异常。
     */
    public static void nioRead(String file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(322222);
        channel.read(buffer);
        byte[] b = buffer.array();
        System.out.println(new String(b));
    }

    public static void main(String[] args) throws IOException{
        nioRead("d:\\sds.txt");
    }
}
