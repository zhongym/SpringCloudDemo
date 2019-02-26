package com.zym.springcloud.user.center.guava;

import com.google.common.cache.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static void main(String[] args) throws Exception {
        Cache<String, String> CACHE =
                CacheBuilder.newBuilder()
                        .softValues()
                        .recordStats()
                        .expireAfterWrite(1, TimeUnit.SECONDS)
                        .build();

        CACHE.put("a","b");
        System.out.println(CACHE.getIfPresent("a"));
        Thread.sleep(1000);
        System.out.println(CACHE.getIfPresent("a"));

        LoadingCache<Long, AtomicInteger> counter = CacheBuilder.newBuilder()
                //缓存回收策略/基于容量
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .softValues()
                //并发级别
//                .concurrencyLevel()
                //启动记录统计信息
                .recordStats()
                .build(new CacheLoader<Long, AtomicInteger>() {
                    @Override
                    public AtomicInteger load(Long key) {
                        return null;
                    }
                });

        //主动移除key
        counter.invalidate(1L);

        counter.put(1L,new AtomicInteger(1));

        counter.get(1L);
        counter.get(2L);

        CacheStats stats = counter.stats();
        System.out.println(stats);
    }
}
