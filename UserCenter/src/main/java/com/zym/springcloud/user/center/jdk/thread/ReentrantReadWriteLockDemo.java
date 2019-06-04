package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        System.out.println("获取读锁");

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        System.out.println("获取写锁");



    }
}
