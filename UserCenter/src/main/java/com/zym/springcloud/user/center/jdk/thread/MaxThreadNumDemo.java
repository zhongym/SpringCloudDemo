package com.zym.springcloud.user.center.jdk.thread;

public class MaxThreadNumDemo {
    public static void main(String[] args) {
        int i = 0;
        try {


            while (true) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                i++;
                System.out.println("i:" + i);
            }
        } catch (Exception e) {
            System.out.println("i:" + i);
            e.printStackTrace();
        }
        //i:151039
    }
}
