package com.zym.springcloud.user.center.lambda;

import java.util.Random;
import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {
//        IntStream.range(0, 10).parallel().forEach(i -> {
//            System.out.println(Thread.currentThread() + ":" + i);
//        });
//        IntStream.generate(()->10).forEach(i->{
//            System.out.println(Thread.currentThread() + ":" + i);
//        });
//        IntStream.iterate(3,(i)->{
//            System.out.println(i);
//            return i+1;
//        }).limit(5).forEach(i->{
////            System.out.println(i);
//        });

//        IntStream.of(1,3).forEach(i->{
//            System.out.println(i);
//        });

        IntStream.generate(() -> new Random().nextInt(10000)).limit(100).forEach(i -> {
            System.out.println(Thread.currentThread() + ":" + i);
        });



    }
}
