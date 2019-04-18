package com.zym.springcloud.user.center.jdk;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {

        Thread.sleep(1000000000);


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
