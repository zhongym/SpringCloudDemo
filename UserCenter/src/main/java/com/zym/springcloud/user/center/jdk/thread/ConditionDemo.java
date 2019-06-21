package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("all")
public class ConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread() + "获得锁");
                try {
                    try {
                        System.out.println(Thread.currentThread() + "释放锁-等待");
                        condition.await();
                        System.out.println(Thread.currentThread() + "重新获得锁");
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread() + "释放锁-结束");
                }

            }
        }.start();

        Thread.sleep(1000);

        lock.lock();
        System.out.println(Thread.currentThread() + "获得锁");

        condition.signal();
        System.out.println(Thread.currentThread() + "通知");
        Thread.sleep(1000);

        lock.unlock();
        System.out.println(Thread.currentThread() + "释放锁-结束");

        Thread.sleep(60000);
    }
}
