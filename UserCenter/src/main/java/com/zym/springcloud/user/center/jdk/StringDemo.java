package com.zym.springcloud.user.center.jdk;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

import java.math.BigDecimal;

/**
 * -Xms40m -Xmx40m -Xmn10m -XX:+PrintGCDetails -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=4m
 */
public class StringDemo {
    private static Interner<Long> pool = Interners.newWeakInterner();
    public static void main(String[] args) {
        int i = new BigDecimal("38.0").intValue();
        System.out.println(i);
         i = new BigDecimal("38").intValue();
        System.out.println(i);

    }
}
