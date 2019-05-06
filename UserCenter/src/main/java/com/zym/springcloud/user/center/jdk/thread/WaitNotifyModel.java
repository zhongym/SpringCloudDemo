package com.zym.springcloud.user.center.jdk.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class Task {
    public int no;

    public Task(int no) {
        this.no = no;
    }
}

public class WaitNotifyModel {
    private final Object BUFFER_LOCK = new Object();
    private final Queue<Task> buffer = new LinkedList<>();
    private final int cap;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public WaitNotifyModel(int cap) {
        this.cap = cap;
    }

    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (BUFFER_LOCK) {
                        System.out.println(Thread.currentThread().getName()+"Consumer获得锁");
                        while (buffer.size() == 0) {
                            System.out.println(Thread.currentThread().getName()+"Consumer等待通知");
                            BUFFER_LOCK.wait();
                        }
                        System.out.println(Thread.currentThread().getName()+"Consumer获得通知");
                        Task task = buffer.poll();
                        assert task != null;
                        // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                        Thread.sleep(500 + (long) (Math.random() * 500));
                        System.out.println("consume: " + task.no);
                        BUFFER_LOCK.notifyAll();
                        System.out.println(Thread.currentThread().getName()+"Consumer通知所有");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private class ProducerImpl implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // 不定期生产，模拟随机的用户请求
                    Thread.sleep((long) (Math.random() * 1000));
                    synchronized (BUFFER_LOCK) {
                        System.out.println(Thread.currentThread().getName()+"Producer获得锁");
                        while (buffer.size() == cap) {
                            System.out.println(Thread.currentThread().getName()+"Producer等待通知");
                            BUFFER_LOCK.wait();
                        }
                        System.out.println(Thread.currentThread().getName()+"Producer获得通知");
                        Task task = new Task(increTaskNo.getAndIncrement());
                        buffer.offer(task);
                        System.out.println("produce: " + task.no);
                        BUFFER_LOCK.notifyAll();
                        System.out.println(Thread.currentThread().getName()+"Producer通知所有1");
                        Thread.sleep(10000);
                        System.out.println(Thread.currentThread().getName()+"Producer通知所有2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyModel model = new WaitNotifyModel(1);
        for (int i = 0; i < 1; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        System.out.println("");
        for (int i = 0; i < 1; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}