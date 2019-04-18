package com.zym.springcloud.user.center.jdk;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max, 0L);

        ExecutorService executor = Executors.newFixedThreadPool(50);

        CopyOnWriteArrayList<Integer> objects = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                int round = (int) (Math.random() * 1000);
                objects.add(round);
                accumulator.accumulate(round);
            });
        }

        Thread.sleep(10000);
        System.out.println(accumulator.get());
        Integer integer = objects.stream().max(Integer::compareTo).orElse(0);
        System.out.println(integer);
        executor.shutdown();


    }
}
