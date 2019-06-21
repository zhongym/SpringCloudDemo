package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.Exchanger;

@SuppressWarnings("all")
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread("thread-1") {
            @Override
            public void run() {
                try {
                    String thread1Data = "thread-1-data";
                    //当线程1交换数据时，如果线程2已经交换，直接返回。否则等待线程2交换后唤醒返回
                    System.out.println(Thread.currentThread().getName() + " 交换数据:" + thread1Data);
                    String thread2Data = exchanger.exchange(thread1Data);
                    System.out.println(Thread.currentThread().getName() + " 获得数据:" + thread2Data);
                } catch (InterruptedException e) {}
            }
        }.start();

        new Thread("thread-2") {
            @Override
            public void run() {
                try {
                    //模拟方法调用费时
                    Thread.sleep(1000);

                    String thread2Data = "thread-2-data";
                    System.out.println(Thread.currentThread().getName() + " 交换数据:" + thread2Data);
                    String thread1Data = exchanger.exchange(thread2Data);
                    System.out.println(Thread.currentThread().getName() + " 获得数据:" + thread1Data);
                } catch (InterruptedException e) {}
            }
        }.start();
    }
}
