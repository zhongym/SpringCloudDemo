package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread("thread1") {
            @Override
            public void run() {
                lock.lock();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.unlock();

            }

        }.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread("thread2") {
            @Override
            public void run() {
                lock.lock();

            }
        }.start();
    }


    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();

    public static void main1(String[] args) throws Exception {

        new Thread("thread1") {
            @Override
            public void run() {
                lock.lock();
                sout("获得锁");
                try {
                    sout("释放锁，等待");
                    condition.await();
                    sout("从新获得锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sout("释放锁");
                lock.unlock();
            }
        }.start();

        Thread.sleep(50);

        new Thread("thread2") {
            @Override
            public void run() {
                lock.lock();
                sout("获得锁");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sout("通知");
                condition.signal();

                sout("释放锁");
                lock.unlock();
            }
        }.start();

        Thread.sleep(50);

        new Thread("thread3") {

            @Override
            public void run() {
                lock.lock();
                sout("获得锁");

                sout("释放锁");
                lock.unlock();
            }
        }.start();

        Thread.sleep(50);
    }

    private static void sout(String a) {
        System.out.println(Thread.currentThread().getName() + ":" + a);
    }
}
