package com.pwz.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/10/2 11:40
 **/
public class T implements Runnable{


    private int aa = 0;
    private static int anInt = 0;
    private static String dd = "";

    @Override
    public  void run() {
        sdf();
    }

    private  void sdf() {
        int a= 0;
        for (int i = 0; i <10000; i++) {

            a++;
            aa++;
            anInt++;


            dd="a";
            dd="b";
            dd="c";
            dd="d";
            dd="d";
            dd="d";
            dd="d";
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dd="d";
            dd="e";
            System.out.println(Thread.currentThread().getName() +" "+ a);
            System.out.println(Thread.currentThread().getName() +"总数 "+ aa);
            System.out.println(Thread.currentThread().getName() +"总数anInt "+ anInt);
            System.out.println(Thread.currentThread().getName() +"总数dd "+ dd);

        }
    }

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        T a = new T();
        executor.execute(a);
        executor.execute(a);
        executor.execute(a);
        executor.execute(a);
        executor.execute(a);
        executor.execute(a);

        executor.shutdown();
    }

}
