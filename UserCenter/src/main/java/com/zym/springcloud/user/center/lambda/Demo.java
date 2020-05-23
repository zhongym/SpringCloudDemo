package com.zym.springcloud.user.center.lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Yuanmao.Zhong
 */
public class Demo {
    private String s;
    private Integer i;
    private Long l;
    private Double d;

    public static void main(String[] args) {


        List<Demo> demos = new ArrayList<>(1000000);
        for (int i = 1; i <= 1000000; i++) {
            demos.add(new Demo("s" + i, i, (long) i, (double) i));
        }

        String result = demos.parallelStream().map(Demo::getI).collect(Collector.of(
                Counter::new,
                Counter::add,
                Counter::combiner,
                Counter::result
        ));

        String result1 = demos.parallelStream().map(Demo::getI).collect(Collector.of(
                () -> new AtomicLong(0),
                (BiConsumer<AtomicLong, Integer>) AtomicLong::addAndGet,
                (l1, l2) -> {
                    System.out.println(Thread.currentThread().getName() + "合并1:" + l2.get());
                    l1.addAndGet(l2.get());
                    return l1;
                },
                l -> "count:" + l.get()
        ));
        System.out.println(result);
        System.out.println(result1);

//        Integer count = demos.parallelStream().map(Demo::getI).reduce(0, Integer::sum);
//        Integer count1 = demos.parallelStream().map(Demo::getI).collect(Collectors.reducing(0, Integer::sum));
//        Integer count2 = demos.parallelStream().collect(Collectors.reducing(0, Demo::getI, Integer::sum));
    }

    public static class Counter {
        //        private AtomicLong count = new AtomicLong(0);
        private long count = 0;

        public void add(int i) {
//            System.out.println(Thread.currentThread().getName() + "处理" + i);
//            count.addAndGet(i);
            count += i;
        }

        public Counter combiner(Counter c) {
//            System.out.println(Thread.currentThread().getName() + "合并2:"+c.count.get());
//            this.count.addAndGet(c.count.get());
            this.count += c.count;
            return this;
        }

        public String result() {
            return "count:" + count;
        }
    }


    public Demo(String s, Integer i, Long l, Double d) {
        this.s = s;
        this.i = i;
        this.l = l;
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }
}
