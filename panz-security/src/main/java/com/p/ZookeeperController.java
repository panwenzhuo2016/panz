package com.p;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hasee on 2018/7/28.
 */
@RestController
public class ZookeeperController {

    @RequestMapping(value = "/zk",method = RequestMethod.GET)
    public String zk(){

        Watcher watcher = (event) ->{
            System.out.println(event);
        };
        String value = null;
        try {
            final ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",10,watcher);
            Stat data2 = zooKeeper.setData("/eee","erewr".getBytes(),3);
            final byte[] data = zooKeeper.getData("/eee",watcher,data2);
            value = new String(data);
            System.out.println(value);
            zooKeeper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        ZookeeperController x = new ZookeeperController();
        x.zk();
    }
}
