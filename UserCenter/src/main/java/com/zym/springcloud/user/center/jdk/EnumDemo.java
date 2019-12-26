package com.zym.springcloud.user.center.jdk;

public enum EnumDemo {
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

    private final int code;

    EnumDemo(int code) {
        this.code = code;
    }

    public static void main(String[] args) {
        System.out.println(EnumDemo.AUTUMN);
    }

}
