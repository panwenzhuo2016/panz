package com.pwz.unsafe;

class StupidCounter implements Counter {
    private volatile long counter = 0;
 
    @Override
    public void increment() {
        counter++;
    }
 
    @Override
    public long getCounter() {
        return counter;
    }
}