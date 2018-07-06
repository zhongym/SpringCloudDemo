package com.zym.springcloud.user.center.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

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
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setPassword("u2CVLsHGYn4dVh6M4qTMr17iaQk");


        RedissonClient client = Redisson.create(config);
        RAtomicLong aLong = client.getAtomicLong("atomicLong");
        long l = aLong.addAndGet(1);
        System.out.println(l);
        long l1 = aLong.incrementAndGet();
        System.out.println(l);

        RMap<Object, Object> map = client.getMap("map");
        map.put("zs", new Student("zsss"));
        map.put("ls", new Student("zsss"));


        RReadWriteLock readWriteLock = client.getReadWriteLock("");
        RLock rLock = readWriteLock.writeLock();

        rLock.lock();

    }
}
