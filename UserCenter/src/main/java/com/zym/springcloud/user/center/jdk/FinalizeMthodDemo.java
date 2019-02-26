package com.zym.springcloud.user.center.jdk;

import java.util.ArrayList;
import java.util.HashMap;

public class FinalizeMthodDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        A demo = new A();
        demo =null;
        System.gc();
        Thread.sleep(1000);

        System.out.println("");

    }



    public static class A{

        @Override
        public void finalize() throws Throwable {
            System.out.println("调用finalize:"+Thread.currentThread().getName());
        }

    }
}
