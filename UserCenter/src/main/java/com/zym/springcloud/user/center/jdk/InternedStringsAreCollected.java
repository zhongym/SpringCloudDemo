package com.zym.springcloud.user.center.jdk;

public class InternedStringsAreCollected {

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            foo();
            System.gc();
        }
    }

    private static void foo() {
        char[] tc = new char[10000];
        for (int i = 0; i < tc.length; i++)
            tc[i] = (char) (i * 136757);
        String s = new String(tc).intern();
        System.out.println(System.identityHashCode(s));
    }
}