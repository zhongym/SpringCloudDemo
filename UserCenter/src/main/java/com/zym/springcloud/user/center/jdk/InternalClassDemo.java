package com.zym.springcloud.user.center.jdk;

/**
 * @author zhongym
 */
public class InternalClassDemo {

    public static void main(String[] args) {
        int i = 0;
        new Thread(() -> System.out.println(i)).start();


    }
}
