package com.zym.springcloud.user.center.jdk.thread;

public class VolatileDemo {

    /**
     * 不加volatile 可能会一直for下去
     */
    private /*volatile*/ boolean isStop;
    private volatile int a;

    @SuppressWarnings("all")
    public void test() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
//                while (!isStop) {
//                }

                int c = a;
                while (!isStop) {
                }
//
//                while (!isStop && a != 1) {
//                }
//
//                while (a != 1 && !isStop) {
//                }
                System.out.println("end");
            }
        }.start();

        Thread.sleep(1000);
        isStop = true;
        a = 1;
        Thread.sleep(1000);
        System.out.println("after a");

//        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileDemo().test();
    }

}
