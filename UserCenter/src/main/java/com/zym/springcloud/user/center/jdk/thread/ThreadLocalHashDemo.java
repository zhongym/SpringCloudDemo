package com.zym.springcloud.user.center.jdk.thread;

public class ThreadLocalHashDemo {

    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        ThreadLocal<String> local1 = new ThreadLocal<>();
        ThreadLocal<String> local2 = new ThreadLocal<>();
        ThreadLocal<String> local3 = new ThreadLocal<>();

        local.set("1");
        local1.set("1");
        local2.set("1");
        local3.set("1");

        ThreadLocal<String> initial = ThreadLocal.withInitial(() -> "aa");
    }
}
