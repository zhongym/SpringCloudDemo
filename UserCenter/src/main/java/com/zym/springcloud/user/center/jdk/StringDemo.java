package com.zym.springcloud.user.center.jdk;

/**
 * @author zhongym
 */
public class StringDemo {
    public static void main(String[] args) {
        String a = "ab";
        String b = "a" + "b";
        String c = String.valueOf("ab");
        System.out.println(a == b);
        System.out.println(a == c);
    }
}
