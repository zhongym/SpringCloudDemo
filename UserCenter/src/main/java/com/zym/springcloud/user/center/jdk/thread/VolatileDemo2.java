package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.TimeUnit;

public class VolatileDemo2 {

    private static volatile boolean isStop = false;

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (!isStop) {
                    i++;
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println("stop:" + i);
            }
        }.start();

        TimeUnit.SECONDS.sleep(3);
        isStop = true;
        System.out.println("stop end");
    }
}
