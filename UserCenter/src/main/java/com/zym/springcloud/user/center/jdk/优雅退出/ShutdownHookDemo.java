package com.zym.springcloud.user.center.jdk.优雅退出;

public class ShutdownHookDemo {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("退出");
            }
        });
        System.out.println("-退出-");
        System.exit(1);
    }
}
