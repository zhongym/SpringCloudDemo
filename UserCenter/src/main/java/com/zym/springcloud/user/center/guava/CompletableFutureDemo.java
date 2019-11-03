package com.zym.springcloud.user.center.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    private static final ExecutorService SINGLE_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            10, 15,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(30),
            new ThreadFactoryBuilder().setNameFormat("task-thread-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    System.out.println(Thread.currentThread().getName() + "->队列已经满，在当前线程运行任务Executor:" + e);
                    super.rejectedExecution(r, e);
                }
            });

    static {
        EXECUTOR.prestartCoreThread();
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        int count = 10;
        List<CompletableFuture<String>> taskList = Lists.newArrayList();
        for (int i = 0; i < count; i++) {
            int finalI = i;
            taskList.add(CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "-> 任务:" + finalI + " start");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-> 任务:" + finalI + " end");
                return finalI + "";
            }, EXECUTOR));
        }
        System.out.println(Thread.currentThread().getName() + "->任务全部提交");
        CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0])).join();
        System.out.println(Thread.currentThread().getName() + "->全部任务完成");
        List<String> resultList = taskList.stream()
                .map(t -> {
                    try {
                        return t.get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(Thread.currentThread().getName() + "->结果：" + resultList);

        long end = System.currentTimeMillis();
        System.out.println("用时ms:" + (end - start));
        EXECUTOR.shutdownNow();
    }

    private static void test() throws InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        CompletableFutureDemo demo = new CompletableFutureDemo();

//
//        //等待所有异步处理完成
//        Map<String, Object> map = Maps.newHashMap();
//        List<CompletableFuture> listC = Lists.newArrayList();
//        listC.add(
//                CompletableFuture.runAsync(() -> {
//                    Long aLong = demo.getLong();
//                    map.put("long", aLong);
//                })
//        );
//
//        listC.add(
//                CompletableFuture.runAsync(() -> {
//                    List<String> list = demo.getArrayList();
//                    map.put("List", list);
//                })
//        );
//        CompletableFuture.allOf(listC.toArray(new CompletableFuture[0]))
//                .join();
//
//        System.out.println(Thread.currentThread().getName() + map);
//
//
        //获取两个结果最快的一个
        Object o = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("---------1");
                    return "1";
                }),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("---------2");
                    return "2";
                })
        ).get(1, TimeUnit.SECONDS);
        System.out.println(o);
//
//
//        List<Long> longs = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "c");
//            return demo.getBillId(1L);
//        }).thenApply((billIds) -> {
//            System.out.println(Thread.currentThread().getName() + "d");
//            return demo.getBill(billIds);
//        }).get();
//
//        System.out.println(longs);
//
//        CompletableFuture<List<Long>> async = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "c");
//            return demo.getBillId(1L);
//        });
//        CompletableFuture<List<Long>> async1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "c");
//            return demo.getBillId(1L);
//        });
//
//        List<Long> longs1 = async.thenCombineAsync(async1, (billId1, billId2) -> {
//            billId1.addAll(billId2);
//            return demo.getBill(billId1);
//        }).get();
//
//        System.out.println(longs1);


        //异步分批查询
        long s = System.currentTimeMillis();
        List<CompletableFuture<List<Long>>> futureList = Lists.newArrayList(1L, 2L)
                .stream()
                .map(id -> CompletableFuture.supplyAsync(() -> demo.getBillId(id)))
                .collect(Collectors.toList());

        CompletableFuture<Void> future = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        future.get(1, TimeUnit.SECONDS);

        List<Long> collect = futureList.stream()
                .map(c -> {
                    try {
                        return c.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ArrayList<Long>();
                    }
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        long e = System.currentTimeMillis();
        System.out.println("花费:" + (e - s));
        System.out.println(collect);
    }

    public List<Long> getBillId(Long storeId) {
        try {
            if (storeId.equals(1L)) {
                Thread.sleep(400);
            } else {
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(storeId);
    }

    public List<Long> getBill(List<Long> billIds) {

        return Lists.newArrayList(billIds);
    }


    public Long getLong() {
        System.out.println(Thread.currentThread().getName() + "---getLong");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public List<String> getArrayList() {
        System.out.println(Thread.currentThread().getName() + "---getArrayList");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }
}
