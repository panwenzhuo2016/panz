package com.pwz.unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/30 18:06
 **/
public class AA {
    public static void main(String[] args) throws Exception{
        sd( new StupidCounter());
        sd( new SyncCounter());
        sd( new LockCounter());
        sd( new AtomicCounter());
        sd( new CASCounter());

    }
    public static void sd(Counter counter) throws Exception{
        int NUM_OF_THREADS = 100;
        int NUM_OF_INCREMENTS = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms:" + (after - before));
    }
}
