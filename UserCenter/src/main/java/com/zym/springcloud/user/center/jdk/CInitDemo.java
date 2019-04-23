package com.zym.springcloud.user.center.jdk;

public class CInitDemo {

    private static int a = 10;

    static {

        System.out.println("a=" + a);
        System.out.println("b=" + CInitDemo.b);
        System.out.println("c=" + CInitDemo.c);

    }

    private static int b = 20;
    private final static int c = 30;

    public static void main(String[] args) {
        System.out.println("b=" + CInitDemo.b);

        /**
         * a=10
         * b=0  // 准备的时候初始化为零值
         * c=30 // 因为c在准备的时候就已经初始化成30了
         * b=20
         */
    }

}
