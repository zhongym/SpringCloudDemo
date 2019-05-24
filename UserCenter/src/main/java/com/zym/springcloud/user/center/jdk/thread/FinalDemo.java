package com.zym.springcloud.user.center.jdk.thread;

public class FinalDemo {

    private final long a = 20;

    public long getA() {
        return a;
    }

    public static void main(String[] args) throws Exception {
        FinalDemo demo = new FinalDemo();
        System.out.println(demo.getA());


        new Thread(){
            @Override
            public void run() {
                System.out.println("sub" + demo.getA());
            }
        }.start();


        new Thread(()->{
            System.out.println("sub" + demo.getA());
        }) .start();
    }
}
