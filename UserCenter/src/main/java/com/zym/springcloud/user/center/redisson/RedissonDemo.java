package com.zym.springcloud.user.center.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.List;

class Student{
    String name;
    Student(){

    }
    Student(String name){
        this.name = name;
    }
}
public class RedissonDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.setNettyThreads(2);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setConnectionPoolSize(1)
                .setConnectionMinimumIdleSize(1)
                .setPassword("u2CVLsHGYn4dVh6M4qTMr17iaQk");


        RedissonClient client = Redisson.create(config);
        client.getKeys();
        RAtomicLong aLong = client.getAtomicLong("atomicLong");
        long l = aLong.addAndGet(1);
        System.out.println(l);
        long l1 = aLong.incrementAndGet();
        System.out.println(l);

        RMap<Object, Object> map = client.getMap("map",MapOptions.defaults());
        map.put("zs", new Student("zsss"));
        map.put("ls", new Student("zsss"));


        RReadWriteLock readWriteLock = client.getReadWriteLock("");
        RLock rLock = readWriteLock.writeLock();

        rLock.lock();

        RBatch batch = client.createBatch(BatchOptions.defaults());
        batch.getSet("").readAllAsync();
        BatchResult<?> execute = batch.execute();
        List<?> responses = execute.getResponses();


        RLock lock = client.getLock("");
        lock.lock();
        lock.unlock();

        client.getHyperLogLog("");
    }
}
