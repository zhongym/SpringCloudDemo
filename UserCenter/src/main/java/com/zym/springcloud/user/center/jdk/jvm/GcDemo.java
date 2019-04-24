package com.zym.springcloud.user.center.jdk.jvm;

/**
 * -Xms40m -Xmx40m -Xmn10m -XX:+PrintGCDetails -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=4m
 */
public class GcDemo {

    public static void main(String[] args) throws InterruptedException {

        byte[] arr = new byte[1024 * 1024 * 25];
        arr = null;
        byte[] arr1 = new byte[1024 * 1024 * 25];

        Thread.sleep(1000000);
    }
}
