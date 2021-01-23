package com.zym.springcloud.user.center.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import javax.annotation.Nonnull;
import java.util.concurrent.*;

/**
 * -Xms40m -Xmx40m -Xmn10m -XX:+PrintGCDetails -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=4m
 */
public class CacheDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Cache<Integer, Integer> CACHE =
//                CacheBuilder.newBuilder()
//                        .softValues()
//                        .maximumSize(10)
//                        .expireAfterWrite(2, TimeUnit.SECONDS)
//                        .build();
//
//        for (int i = 0; i < 1000000000; i++) {
//            CACHE.put(i, i);
//            System.out.println(CACHE.asMap());
//        }
//
//        CACHE.get(1, () -> null);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();


        LoadingCache<String, Object> CACHE1 =
                CacheBuilder.newBuilder()
                        .softValues()
                        .expireAfterWrite(4, TimeUnit.SECONDS)
                        .refreshAfterWrite(2, TimeUnit.SECONDS)
                        .build(new CacheLoader<String, Object>() {
                            @Override
                            public Object load(@Nonnull String key) {
                                System.out.println(Thread.currentThread().getName() + "-->同步加载");
                                return get();
                            }

                            @Override
                            public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-->异步刷新");
//                                ListenableFutureTask<Object> task = ListenableFutureTask.create(new Callable<Object>() {
//                                    @Override
//                                    public Object call() throws Exception {
//                                        return get();
//                                    }
//                                });
//                                executor.submit(task);
//                                return task;
                                return Futures.immediateFuture(get());
                            }
                        });

//        System.out.println(CACHE1.get("a"));
//        System.out.println(CACHE1.get("a"));
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(CACHE1.get("a"));
//        TimeUnit.MILLISECONDS.sleep(301);
//        System.out.println(CACHE1.get("a"));
//
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(CACHE1.get("a"));

        System.out.println(Thread.currentThread().getName() + "--->" + CACHE1.get("a"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "--->" + CACHE1.get("a"));
        System.out.println(Thread.currentThread().getName() + "--->" + CACHE1.get("a"));
        TimeUnit.SECONDS.sleep(1);
        for (int j = 0; j < 3; j++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "--->" + CACHE1.get("a"));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


    }

    static int i = 0;

    private static Object get() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i++;
    }

}
