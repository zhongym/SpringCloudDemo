package com.zym.springcloud.user.center.jdk;

public class FinallyDemo {

    public int getA() {
        int i = 0;
        try {
            i = i / 0;
            return i;
        } catch (Exception e) {
            return i;
        } finally {
            i = i + 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FinallyDemo().getA());
    }
}
