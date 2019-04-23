package com.zym.springcloud.user.center.jdk;

public class CInitDemo1 {

    static {
        if (1 == 1) {
            throw new RuntimeException("");
        }
    }

    public static void main(String[] args) {
        /**
         * java.lang.ExceptionInInitializerError
         * Caused by: java.lang.RuntimeException:
         * 	at com.zym.springcloud.user.center.jdk.CInitDemo1.<clinit>(CInitDemo1.java:7)
         */
        //证明 static{}组合在 CInitDemo1.<clinit>()方法

    }
}
