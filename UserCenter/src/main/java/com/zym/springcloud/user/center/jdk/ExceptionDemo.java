package com.zym.springcloud.user.center.jdk;

public class ExceptionDemo {

    public static void main(String[] args) throws Exception {

        int i = 10;

        int i1 = i++;

        int j = 20;

        int i2 = ++j;

        System.out.println(i);
        System.out.println(i1);
        System.out.println(j);
        System.out.println(i2);
    }

}
