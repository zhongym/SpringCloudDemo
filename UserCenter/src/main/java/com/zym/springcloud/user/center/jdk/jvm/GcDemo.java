package com.zym.springcloud.user.center.jdk.jvm;

/**
 * -Xms40m -Xmx40m -Xmn10m -XX:+PrintGCDetails -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=4m
 */
public class GcDemo {

    public static void main(String[] args) throws InterruptedException {

       new Thread(){
           @Override
           public void run() {
               while (true){
                   System.out.println("runing");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                try {

                    byte[] arr = new byte[1024 * 1024 * 30];
                }catch (OutOfMemoryError e){
                    e.printStackTrace();
                }
                System.out.println("-----------------");
            }
        }.start();

    }
}
