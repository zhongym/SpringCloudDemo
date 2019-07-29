package com.zym.springcloud.user.center.jdk;

public class MultiParamDemo {

    public static void main(String[] args) {
        test("b");
        test();
    }

    public static void test(String ...strs){
        int length = strs.length;
        System.out.println(length);

        byte[] bytes = "中国".getBytes();
        System.out.println(bytes);
    }
}
