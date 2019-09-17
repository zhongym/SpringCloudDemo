package com.zym.springcloud.user.center.jdk.thread;

public class FinalDemo1 {

    private static final long a = getA();

    public static long getA() {
        return 20;
    }

    public static void main(String[] args) throws Exception {

        while (true) {
            if (a <= 20) {
                System.out.println("a");
            }
        }
    }
}
