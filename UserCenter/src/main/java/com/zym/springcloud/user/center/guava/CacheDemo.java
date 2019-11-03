package com.zym.springcloud.user.center.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

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
                                ListenableFutureTask<Object> task = ListenableFutureTask.create(new Callable<Object>() {
                                    @Override
                                    public Object call() throws Exception {
                                        return get();
                                    }
                                });
                                executor.submit(task);
                                return task;
                            }
                        });

        System.out.println(CACHE1.get("a"));
        System.out.println(CACHE1.get("a"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(CACHE1.get("a"));
        TimeUnit.MILLISECONDS.sleep(301);
        System.out.println(CACHE1.get("a"));

        TimeUnit.SECONDS.sleep(5);
        System.out.println(CACHE1.get("a"));

    }

    static int i = 0;

    private static Object get() {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i++;
    }

}
