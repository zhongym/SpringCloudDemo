package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    @SuppressWarnings("all")
    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() {
                return null;
            }
        });
        Thread thread = new Thread(task);
        thread.start();

    }
}
