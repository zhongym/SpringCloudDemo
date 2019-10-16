package com.zym.springcloud.user.center.jdk;

import java.math.BigDecimal;

public class IsNaNDemo {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal(0.0);
        BigDecimal d2 = new BigDecimal(0.0/0.0);
        BigDecimal d3 = d1.divide(d2);
        System.out.println(d3);
    }
}
