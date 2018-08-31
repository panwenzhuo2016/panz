package com.pwz.unsafe;

import java.util.concurrent.atomic.AtomicLong;

class AtomicCounter implements Counter {
    AtomicLong counter = new AtomicLong(0);
 
    @Override
    public void increment() {
        counter.incrementAndGet();
    }
 
    @Override
    public long getCounter() {
        return counter.get();
    }
}