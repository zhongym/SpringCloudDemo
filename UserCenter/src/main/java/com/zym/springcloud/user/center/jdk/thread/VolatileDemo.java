package com.zym.springcloud.user.center.jdk.thread;

public class VolatileDemo {

    /**
     * 不加volatile 可能会一直for下去
     */
    private /*volatile*/ boolean isStop;

    @SuppressWarnings("all")
    public void test() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (!isStop) {
                }
                System.out.println("end");
            }
        }.start();

        Thread.sleep(1000);

        isStop = true;

//        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileDemo().test();
    }

}
