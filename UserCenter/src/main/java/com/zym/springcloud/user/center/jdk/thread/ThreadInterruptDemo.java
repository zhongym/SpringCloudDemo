package com.zym.springcloud.user.center.jdk.thread;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@SuppressWarnings("all")
public class ThreadInterruptDemo {

    private String name;

    public static void main(String[] args) throws InterruptedException {
//        thread();
//        threadPool();

        ThreadInterruptDemo demo = new ThreadInterruptDemo();
        demo.name= "a";
        System.out.println(demo);

        String id = null;
        if (id == null) {

        }

        String id1 = "";
        if (id1 == null && id1 != "") {

        }

        if (StringUtils.isNotEmpty(id1)) {

        }

    }

    private static void threadPool() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));
        System.out.println(executor.toString());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                }
            }
        });
        System.out.println(executor.toString());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {

                }
            }
        });
        System.out.println(executor.toString());

        executor.shutdown();
        System.out.println(executor.toString());

        executor.shutdownNow();
        System.out.println(executor.toString());

        Thread.sleep(100);
        System.out.println(executor.toString());
    }


    private static void thread() throws InterruptedException {
        Thread waitThread = new Thread("waitThread-interrupt") {
            @Override
            public void run() {
                synchronized (this) {
                    sout("线程执行：" + Thread.currentThread().isInterrupted());
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        sout("线程被中断了，继续执行：" + Thread.currentThread().isInterrupted());
                    }
                }
            }
        };

        Thread parkThread = new Thread("parkThread-interrupt") {
            @Override
            public void run() {
                sout("线程执行：" + Thread.currentThread().isInterrupted());
                LockSupport.park();
                sout("线程被中断了，继续执行：" + Thread.currentThread().isInterrupted());
            }
        };

        Thread runningThread = new Thread("runningThread-interrupt") {
            @Override
            public void run() {
                sout("线程执行：" + Thread.currentThread().isInterrupted());
                while (true) {
                    if (this == parkThread) {
                        break;
                    }
                }
                sout("线程被中断了，继续执行：" + Thread.currentThread().isInterrupted());
            }
        };

        //自己处理线程中断状态
        Thread runningThread1 = new Thread("runningThread1-interrupt") {
            @Override
            public void run() {
                sout("线程执行：" + Thread.currentThread().isInterrupted());
                while (!Thread.interrupted()) {
                }
                sout("线程被中断了，继续执行：" + Thread.currentThread().isInterrupted());
            }
        };

        waitThread.start();
        parkThread.start();
        runningThread.start();
        runningThread1.start();

        Thread.sleep(100);
        waitThread.interrupt();
        parkThread.interrupt();
        runningThread.interrupt();
        runningThread1.interrupt();
        sout("中断线程");
    }

    public static void sout(String mes) {
        System.out.println(Thread.currentThread().getName() + "  " + mes);
    }
}
