package com.zym.springcloud.user.center.jdk.thread;

public class InheritableThreadLocalDemo {

    static ThreadLocal<String> local = new ThreadLocal<>();
    static ThreadLocal<String> inheritable_local = new InheritableThreadLocal<>();

    @SuppressWarnings("all")
    public static void main(String[] args) {

        local.set("main thread value");
        inheritable_local.set("main thread value");

        new Thread(() ->{
            System.out.println("sub thread print local:" + local.get());
            System.out.println("sub thread print inheritable_local:" + inheritable_local.get());
        } ).start();
    }
}
