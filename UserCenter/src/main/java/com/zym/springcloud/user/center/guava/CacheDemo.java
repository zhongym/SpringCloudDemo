package com.zym.springcloud.user.center.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
    public static void main(String[] args) throws ExecutionException {
        Cache<Integer, By> CACHE =
                CacheBuilder.newBuilder()
                      .softValues()
                        .recordStats()
                        .expireAfterWrite(1, TimeUnit.SECONDS)
                        .build();

        for (int i = 0; i < 1000000000; i++) {
            CACHE.put(i, new By());
            System.out.println(i);
        }

        CACHE.get(1, () -> null);
    }

    static class By {
        private byte[] arr = new byte[1024 * 1024 * 100];
    }
}
