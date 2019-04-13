package com.zym.springcloud.user.center.jdk;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        lock.lock();
        System.out.println("main");

        new Thread(()->{
            lock.lock();
            System.out.println("suThread");
            lock.unlock();
        }).start();

        Thread.sleep(10000);

        lock.unlock();

    }
}
