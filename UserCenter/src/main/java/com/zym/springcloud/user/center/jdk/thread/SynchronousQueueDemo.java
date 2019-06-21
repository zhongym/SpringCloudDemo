package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);

        new Thread() {
            @Override
            public void run() {
                Integer poll = null;
                try {
                    poll = queue.poll(1000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费" + poll);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Integer poll = null;
                try {
                    poll = queue.poll(1000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费" + poll);
            }
        }.start();

        Thread.sleep(1000);
        queue.offer(1);
        System.out.println("生产" + 1);
        boolean offer = true;


    }



}
