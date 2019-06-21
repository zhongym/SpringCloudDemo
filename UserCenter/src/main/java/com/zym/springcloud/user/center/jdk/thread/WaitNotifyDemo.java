package com.zym.springcloud.user.center.jdk.thread;

@SuppressWarnings("all")
public class WaitNotifyDemo {

    private static Object lock = new Object();

    public static void main(String[] args) throws Exception {

        new Thread("wait-1") {
            @Override
            public void run() {
                synchronized (lock) {
                    sout("获得锁");

                    try {
                        sout("wait-释放锁");
                        lock.wait();

                        sout("重新获得锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sout("退出synchronized{}释放锁");
                }
            }
        }.start();

        new Thread("wait-2") {
            @Override
            public void run() {
                synchronized (lock) {
                    sout("获得锁");

                    try {
                        sout("wait-释放锁");
                        lock.wait();

                        sout("重新获得锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sout("退出synchronized{}释放锁");
                }
            }
        }.start();

        Thread.sleep(10);
        synchronized (lock){
            sout("获得锁");
            lock.notifyAll();
            sout("通知完成");
            Thread.sleep(1000);
            sout("退出synchronized{}释放锁");
        }


        Thread.sleep(1000);
    }

    private static void sout(String m) {
        System.out.println(Thread.currentThread() + " - " + m);
    }
}
