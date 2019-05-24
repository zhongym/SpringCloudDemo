package com.zym.springcloud.user.center.jdk.thread;

public class NotifyDemo {

    static Object lock = new Object();

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread() {
            @Override
            public void run() {

                synchronized (lock) {
                    System.out.println("threadA get resourceA lock");

                    System.out.println("threadA begin wait");
                    try {
                        lock.wait();

                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("threadA end wait");
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {

                synchronized (lock) {
                    System.out.println("threadB get resourceA lock");

                    System.out.println("threadB begin wait");
                    try {
                        lock.wait();

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadB end wait");
                }
            }
        };

        Thread threadD = new Thread() {
            @Override
            public void run() {

                synchronized (lock) {
                    System.out.println("threadD get resourceA lock");

                    System.out.println("threadD begin wait");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("threadD end wait");
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {

                synchronized (lock) {
                    System.out.println("threadC notify");
                    lock.notifyAll();
                }
            }
        };


        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();

        threadD.start();



        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main en");

    }

}