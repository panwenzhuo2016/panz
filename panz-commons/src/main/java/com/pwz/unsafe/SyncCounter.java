package com.pwz.unsafe;

class SyncCounter implements Counter {
    private volatile long counter = 0;
 
    @Override
    public synchronized void increment() {
        counter++;
    }
 
    @Override
    public long getCounter() {
        return counter;
    }
}