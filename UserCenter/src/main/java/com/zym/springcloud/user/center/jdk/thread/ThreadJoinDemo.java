package com.zym.springcloud.user.center.jdk.thread;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("sub thread start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sub thread end");
            }
        };
        thread.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (thread){
                    thread.notifyAll();
                }
                System.out.println("sub thread2 end");
            }
        }.start();



        try {
            Thread.sleep(10);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("join end");
    }
}
