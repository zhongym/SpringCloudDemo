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
}
