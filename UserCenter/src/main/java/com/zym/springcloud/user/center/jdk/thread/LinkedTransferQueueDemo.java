package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueDemo {

    public static void main(String[] args) {
        LinkedTransferQueue<Integer> q = new LinkedTransferQueue<>();

        q.offer(1);
        System.out.println(q);
        q.offer(1);
    }
}
