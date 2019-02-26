package com.zym.springcloud.user.center.jdk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    static class Producer implements Runnable {
        private final BlockingQueue queue;

        Producer(BlockingQueue q) {
            queue = q;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    queue.put(produce());
                }
            } catch (InterruptedException ex) {
            }
        }

        Object produce() {
            System.out.println("生产对象" + Thread.currentThread().getName());
            return 1;
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;

        Consumer(BlockingQueue q) {
            queue = q;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                }
            } catch (InterruptedException ex) {
            }
        }

        void consume(Object x) {
            System.out.println("消费对象：" + x + ":" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(10);
        Producer p = new Producer(q);
        Producer p1= new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
//        new Thread(p).start();
//        new Thread(p1).start();
//        new Thread(c1).start();
        new Thread(c2).start();
    }
}
