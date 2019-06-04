package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        int threadCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {
            int count = 0;
            //此方法在每阶段最后一个完成的线程中执行
            @Override
            public void run() {
                count++;
                if (count == 1) {
                    System.out.println("所有start完成:" + Thread.currentThread());
                }
                if (count == 2) {
                    System.out.println("所有do完成:" + Thread.currentThread());
                }
                if (count == 3) {
                    System.out.println("所有end完成:" + Thread.currentThread());
                }
            }
        });

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    System.out.println("start：" + Thread.currentThread());
                    barrier.await();

                    System.out.println("do：" + Thread.currentThread());
                    barrier.await();

                    System.out.println("end：" + Thread.currentThread());
                    barrier.await();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        }
    }
}
