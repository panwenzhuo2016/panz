package com.pwz.util.exec;

import com.google.common.collect.Maps;
import com.pwz.util.exec.threads.TaskQueue;
import com.pwz.util.exec.threads.TaskThreadFactory;
import com.pwz.util.exec.threads.ThreadPoolExecutor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/11/3 16:44
 **/
public class ExecutorU {
    private static Map<String, ThreadPoolExecutor> pool = Maps.newConcurrentMap();

    public static void newExecutor(String id, String namePrefix) {
        if (pool.get(id) == null) {
            TaskQueue taskqueue = new TaskQueue();
            TaskThreadFactory tf = new TaskThreadFactory(namePrefix + "-exec-", true, Thread.NORM_PRIORITY);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                    60, TimeUnit.SECONDS, taskqueue, tf);
            taskqueue.setParent(executor);
            pool.put(id, executor);
        }
    }

    public static ThreadPoolExecutor getExecutor(String id) {
        if (pool.get(id) == null) {
            newExecutor(id, "ExecutorU-" + id + "-");
        }
        return pool.get(id);
    }

    public static void block(String id){
        while (true){
            if(pool.get(id) == null){
                break;
            }
            if(pool.get(id).getSubmittedCount() == 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        getExecutor("sdfsdf").execute(new Task());
        block("sdfsdf");
        System.out.println("44444444");
    }
    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("11111111");
        }
    }
}
