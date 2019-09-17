package com.zym.springcloud.activity.center;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

public class RedissonDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.setNettyThreads(2);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setConnectionPoolSize(1)
                .setConnectionMinimumIdleSize(1)
                .setPassword("u2CVLsHGYn4dVh6M4qTMr17iaQk");


        RedissonClient client = Redisson.create(config);
        Iterable<String> keys = client.getKeys().getKeys();
        RAtomicLong aLong = client.getAtomicLong("atomicLong");
        long l = aLong.addAndGet(1);
        System.out.println(l);
        long l1 = aLong.incrementAndGet();
        System.out.println(l);



        RReadWriteLock readWriteLock = client.getReadWriteLock("");
        RLock rLock = readWriteLock.writeLock();

        rLock.lock();

    }
}
