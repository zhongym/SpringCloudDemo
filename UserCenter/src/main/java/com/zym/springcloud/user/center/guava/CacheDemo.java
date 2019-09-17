package com.zym.springcloud.user.center.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
    public static void main(String[] args) throws ExecutionException {
        Cache<Integer, Integer> CACHE =
                CacheBuilder.newBuilder()
                      .softValues()
                        .maximumSize(10)
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .build();

        for (int i = 0; i < 1000000000; i++) {
            CACHE.put(i, i);
            System.out.println(CACHE.asMap());
        }

        CACHE.get(1, () -> null);
    }

}
