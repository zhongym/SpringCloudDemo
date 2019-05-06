package com.zym.springcloud.user.center.jdk.jvm.javac;

public class ForDemo {

    public void test1() {
        for (; ; ) {
            System.out.println("aaa");
        }

    }

    public void test2() {
        while (true) {
            if (getB()){
                continue;
            }
            System.out.println("aaa");
        }
    }

    private boolean getB() {
        return true;
    }

    public static void main(String[] args) {


    }
}
