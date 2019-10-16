package com.zym.springcloud.user.center.jdk;

import java.nio.ByteBuffer;

public class DirectByteBufferDemo {

    public static void main(String[] args) {
        ByteBuffer direct = ByteBuffer.allocateDirect(100);
        direct = ByteBuffer.allocateDirect(100);
        System.gc();
        System.out.println("----------->");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();

        new Thread();

        //

    }
}
