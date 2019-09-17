package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.TimeUnit;

public class VolatileDemo3 {

    private static /*volatile*/ int a = 0;

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    a++;
                }
            }
        }.start();

        TimeUnit.SECONDS.sleep(3);

        while (true) {
            int a1 = a;
            Thread.yield();
            int a2 = a;
            if (a1 == a2) {
                System.out.println("==" + a1);
            } else {
                System.out.println(a1 + "!=" + a2);
            }
        }
    }
}
