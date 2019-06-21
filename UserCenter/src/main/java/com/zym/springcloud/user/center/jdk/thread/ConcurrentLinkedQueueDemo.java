package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        System.out.println(queue);
        queue.add("a");
        System.out.println(queue);
        queue.add("b");
        System.out.println(queue);

        String poll = queue.poll();
        String poll1 = queue.poll();

        int a =0;
        int b=1;

         a = b =10;

        System.out.println(a);
        System.out.println(b);

    }
}
