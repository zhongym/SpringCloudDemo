package com.zym.springcloud.user.center.jdk.thread;

import java.time.LocalTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class UIThreadDemo {

    LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void loop() {
        while (true) {
            try {
                Runnable take = queue.take();
                take.run();

            } catch (Exception e) {

            }
        }
    }


    public static void main(String[] args) {
        UIThreadDemo demo = new UIThreadDemo();
        for (long i = 0; i < 5; i++) {
            long finalI = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String subThreadName = Thread.currentThread().getName();
                    demo.queue.add(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(LocalTime.now().getSecond()+"-->"+Thread.currentThread().getName() + "->>subThreadName:" + subThreadName);
                        }
                    });
                }
            }.start();
        }

        demo.loop();
    }
}
