package com.zym.springcloud.user.center.guava;

import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class AsyncFutureDemo {

    @Scheduled
    public static void main(String[] args) throws Exception {

        AsyncFutureDemo demo = new AsyncFutureDemo();
        long s = System.currentTimeMillis();

        Long aLong = demo.getLong();
        List<String> arrayList = demo.getArrayList();

        long e = System.currentTimeMillis();
        System.out.println("同步调用用时:" + (e - s));

        //AsyncFuture
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        s = System.currentTimeMillis();
        Future<Long> longFuture = executorService.submit(demo::getLong);
        System.out.println("submit->longFuture");
        Future<List<String>> listFuture = executorService.submit(demo::getArrayList);
        Long aLong1 = longFuture.get(5, TimeUnit.SECONDS);
        System.out.println("get->longFuture");
        List<String> list = listFuture.get(5, TimeUnit.SECONDS);

        e = System.currentTimeMillis();
        System.out.println("AsyncFuture调用用时:" + (e - s));


//        Map<String,Object> map = new HashMap<>();
//        s = System.currentTimeMillis();
//        executorService.submit(()->{
//            Long aLong2 = demo.getLong();
//            map.put("long",aLong2);
//        });
//        System.out.println("submit->longFuture");
//        Future<?> future = executorService.submit(() -> {
//            List<String> list2 = demo.getArrayList();
//            map.put("list2", list2);
//        });
//        System.out.println(map);


        e = System.currentTimeMillis();
        System.out.println("AsyncFuture调用用时:" + (e - s));
    }


    public Long getLong() {
        System.out.println("getLong");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public List<String> getArrayList() {
//        System.out.println("getArrayList");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }
}
