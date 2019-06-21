package com.zym.springcloud.user.center.jdk.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        new ScheduledThreadPoolExecutorDemo().testBase();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void testBase(){
        ScheduledThreadPoolExecutor threadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        threadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "开始执行1 =" + format(new Date()));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "开始结束执行1 =" + format(new Date()));
            }
        }, 0, 5, TimeUnit.SECONDS);

        threadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "开始执行2 =" + format(new Date()));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "开始结束执行2 =" + format(new Date()));
            }
        }, 4, 5, TimeUnit.SECONDS);
    }

    @SuppressWarnings("all")
    private void testFixedRate() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

        System.out.println("提交任务:" + format(new Date()));
        threadPool.scheduleAtFixedRate(new Runnable() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public void run() {
                int i = count.incrementAndGet();
                System.out.println(Thread.currentThread() + "开始执行 " + i + "次:" + format(new Date()));
                try {
                    if (i > 2) {
                        Thread.sleep(7000);
                    } else {
                        //执行时间2s
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 1, 5, TimeUnit.SECONDS);

        /**
         *
         *
         *
initialDelay=1,delay=5，unit=TimeUnit.SECONDS，任务的执行时间 前2次执行时间为2s,后面执行时间为7s

提交任务:2019-06-01 15:53:15
//任务提交时间

Thread[pool-1-thread-1,5,main]开始执行 1次:2019-06-01 15:53:16  //执行用时2s
//任务第一次计划开始执行时间 = 任务提交时间 + initialDelay 【15 + 1 =16】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(16,0) = 16】
//结束时间 = 执行开始时间 + 用时 【16+2=18】
         
Thread[pool-1-thread-1,5,main]开始执行 2次:2019-06-01 15:53:21 //执行用时2s
//任务第二次计划开始执行时间 = 上一次执行开始时间 + period 【16 +5 = 21】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(21,18) = 21】
//结束时间 = 执行开始时间 + 用时 【21+2=23】
         
Thread[pool-1-thread-2,5,main]开始执行 3次:2019-06-01 15:53:26 //执行用时7s
//任务第三次计划开始执行时间 = 上一次执行开始时间 + period 【21 + 5 = 26】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(26,23) = 26】
//结束时间 = 执行开始时间 + 用时 【26+7=33】

Thread[pool-1-thread-1,5,main]开始执行 4次:2019-06-01 15:53:33 //执行用时7s
//任务第四次计划开始执行时间 = 上一次执行开始时间 + period 【26 + 5 = 31】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(31,33) = 33】
//结束时间 = 执行开始时间 + 用时 【33+7=40】
         
Thread[pool-1-thread-3,5,main]开始执行 5次:2019-06-01 15:53:40 //执行用时7s
//任务第五次计划开始执行时间 = 上一次执行开始时间 + period 【31 + 5 = 36】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(36,40) = 40】
//结束时间 = 执行开始时间 + 用时 【40+7=47】

Thread[pool-1-thread-2,5,main]开始执行 6次:2019-06-01 15:53:47 //执行用时7s
//任务第六次计划开始执行时间 = 上一次执行开始时间 + period 【36 + 5 = 41】
//实际开始执行时间 = max(这次执行时间，上次结束时间) 【max(47,41) = 47】
//结束时间 = 执行开始时间 + 用时 【47+7=52】        
         *
         *
         */

    }

    @SuppressWarnings("all")
    private void testFixedDelay() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

        System.out.println("提交任务:" + format(new Date()));
        threadPool.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread() + "开始执行:" + format(new Date()));
            try {
                //执行时间2s
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 5, TimeUnit.SECONDS);

        /**
         *
         *
         *
         initialDelay=1,delay=5，unit=TimeUnit.SECONDS，任务的执行时间为2

         提交任务:2019-06-01 15:36:28  //任务提交时间
         开始执行:2019-06-01 15:36:29  //任务第一次执行时间 = 任务提交时间 + initialDelay 【28 + 1 =29】
         开始执行:2019-06-01 15:36:36  //任务后序执行时间 = 上一次执行结束时间（上一次执行开始时间+任务的执行时间 【29+2=31】） + delay 【31 + 5 =36】
         开始执行:2019-06-01 15:36:43  //任务后序执行时间 = 上一次执行结束时间（上一次执行开始时间+任务的执行时间 【36+2=38】） + delay 【38 + 5 =43】
         *
         *
         */

    }

    private String format(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
    }
}
