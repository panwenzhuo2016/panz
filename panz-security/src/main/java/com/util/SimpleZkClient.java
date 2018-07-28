package com.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class SimpleZkClient {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    static ZooKeeper zkClient = null;
    private static Object lock = new Object();
    private SimpleZkClient(){

    }
    public static ZooKeeper getInstance(){
        synchronized (lock){
            get();
        }
        return zkClient;
    }
    private static ZooKeeper get(){
        try {
            if(zkClient == null){
                zkClient = new ZooKeeper("127.0.0.1:2181", 2000, null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return zkClient;
    }
    public static boolean exists(String path) throws Exception{
        if(zkClient.exists(path, false) == null ){
            System.out.println("节点 " + path + " 不存在");
            return  false;
        }else {
            System.out.println("节点 " + path + " 存在");
            return true;
        }
    }
    public static void createNode(String path,String msg) throws Exception{
        System.out.println("=========创建节点=========== "+path);
        if (zkClient.exists(path, false) == null) {
            System.out.println("=========创建节点成功===========");
            zkClient.create(path, msg.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else {
            System.out.println(path+" =========节点已存在===========");
        }
    }
    public static void set(String path,String msg) throws Exception{
        zkClient.setData(path, msg.getBytes(), -1);
    }
    public static void delete(String path) throws Exception{
        System.out.println("=======删除节点==========");
        zkClient.delete(path, -1);
    }
    public static void close() throws Exception{
        zkClient.close();
    }
    public static String get(String path) throws Exception{
        return new String(zkClient.getData(path, false, null));
    }

    public static void main(String[] args) throws Exception {
        SimpleZkClient.getInstance();
        SimpleZkClient.exists("/a");
//        SimpleZkClient.createNode("/a","dfdf");
//        SimpleZkClient.exists("/a");

    }

}