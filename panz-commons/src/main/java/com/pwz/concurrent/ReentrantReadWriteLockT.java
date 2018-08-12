package com.pwz.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hasee on 2018/8/4.
 */
public class ReentrantReadWriteLockT {

    private Map<String,Object> map = new ConcurrentHashMap<>();

    public synchronized Object getData(String key){
        Object result = map.get(key);
        if(result ==null){
            result = "new";
            //用这步代替访问数据库得数据
        }
        return result;

    }
    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    public Object getData2(String key){
        rw.readLock().lock();//在读前先上读锁
        Object result = null;
        try{
            result = map.get(key);
            //这个if比较关键，它避免了多余的几次对数据哭的读取
            if(result==null){
                //如果内存中没有所要数据
                rw.readLock().unlock();
                rw.writeLock().lock();
                //为什么要用第二个if呢？再假设一个场景，现在有十个线程来读这个数据，而这个
                // 数据又不存在与缓存区，那么这十个线程中最先到的线程将执行“rw.writeLock
                // ().lock();”而另外九个线程将被阻塞，当第一个线程读完以后缓存区实际上
                // 已经就有了这个数据，但另外九个阻塞在“rw.writeLock().lock();”如果不
                // 加这层if他们会继续访问数据库，由此可见加了这层if对整个过程影响很大
                if(result==null){
                    try{
                        //我们用这个代替对数据库访问得到数据的步骤
                        result = "new";
                    }finally{
                        rw.writeLock().unlock();
                    }
                    rw.readLock().lock();
                }
            }
        }finally{
            rw.readLock().unlock();
        }
        return result;

    }
}
