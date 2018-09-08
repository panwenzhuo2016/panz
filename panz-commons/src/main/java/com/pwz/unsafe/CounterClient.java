package com.pwz.unsafe;

import com.pwz.myGenerator.Log;

class CounterClient implements Runnable {
    private volatile Counter c;
    private int num;
    Log log = new Log("qqqq");
 
    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }
 
    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
            log.info(i+"");
            log.write2Path();
        }
    }
}