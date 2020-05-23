package com.zym.springcloud.user.center.jdk.thread;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println("start:" + Thread.currentThread().getName());
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end:" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "-" + Thread.currentThread().getName());
                    semaphore.release();
                }
            }.start();
        }
    }

    private static Semaphore semaphore1 = new Semaphore(0);
    private static Semaphore semaphore2 = new Semaphore(0);

    public void test1() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划新需求");
                semaphore1.release();
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore1.acquire();
                    System.out.println("开发人员开发新需求功能");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore2.acquire();
                    System.out.println("测试人员测试新功能");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();

        System.out.println("开发人员来上班了...");
        thread2.start();

        System.out.println("产品经理来上班了...");
        thread1.start();

    }
}
