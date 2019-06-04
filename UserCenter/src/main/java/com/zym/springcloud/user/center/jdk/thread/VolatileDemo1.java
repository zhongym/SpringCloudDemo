package com.zym.springcloud.user.center.jdk.thread;

public class VolatileDemo1 {
    private int a;
    private volatile int b;

    void thread1(){
        a = 1;
        b =2;
    }

    void thread2(){
        int a = this.a;
        int b = this.b;
    }
}
