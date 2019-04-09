package com.zym.springcloud.user.center.guava;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadDemo {
    public static void main(String[] args) {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(100);
//        executor.setQueueCapacity(1000);
//        executor.initialize();
//
//        for (int i = 0; i < 10000; i++) {
//            executor.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        System.out.println("----------");
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        executor.shutdown();

        Thread a = new Thread(()->{
            System.out.println("a");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b");
        });
        a.setDaemon(true);
        a.start();


        System.out.println("------------------");
        try {
            a.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
