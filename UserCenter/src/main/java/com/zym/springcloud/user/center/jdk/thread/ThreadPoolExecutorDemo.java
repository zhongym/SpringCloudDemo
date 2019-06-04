package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.*;

@SuppressWarnings("all")
public class ThreadPoolExecutorDemo {


    public static void main(String[] args) {
//        testBase();
//        testnewCachedThreadPool();
        testSetmMum();
    }

    private static void testSetmMum() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                15,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5));

        executor.prestartAllCoreThreads();
        System.out.println(executor);

        executor.setCorePoolSize(10);
        System.out.println(executor);
    }

    private static void testnewCachedThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "执行" + this);
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return "任务" + finalI;
                }
            });
        }
    }

    private static void testBase() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                15,
                15,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("拒绝任务r:" + r + "," + executor.toString());
                    }
                });

        for (int i = 0; i < 20; i++) {
            if (i == 4) {
                System.out.println("");
            }
            if (i == 9) {
                System.out.println("");
            }

            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "执行" + this);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return "任务" + finalI;
                }
            });
        }
    }
}
