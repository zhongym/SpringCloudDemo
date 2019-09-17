package com.zym.springcloud.user.center.jdk;

import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        BigInteger intMax = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger intMax1 = BigInteger.valueOf(Integer.MAX_VALUE + 1L);
        BigInteger and = intMax1.and(intMax);
        BigInteger subtract = intMax1.subtract(intMax);
        BigInteger multiply = intMax1.multiply(intMax);
        BigInteger divide = intMax1.divide(intMax);

        System.out.println(intMax1.toString());
        BigInteger longMax = BigInteger.valueOf(Long.MAX_VALUE);

        BigInteger integer = new BigInteger("123123132131231232132131231232123423412456789");
        BigInteger integer1 = new BigInteger("-123123132131231232132131231232123423412456789");
        System.out.println(integer);
        System.out.println(integer1);
    }
}
