package com.zym.springcloud.user.center.jdk;

public class IfDemo {

    public static void main(String[] args) {
        int i = getI();
        if (i < 0) {
            System.out.println("aaa");
        }

        IfDemo d = getD();
        if (d == null) {
            System.out.println("null");
        } else {
            int a = 20;
            int b = a * 30;
            System.out.println(b);
        }
    }

    public static IfDemo getD() {
        IfDemo ifDemo = new IfDemo();
        return ifDemo;
    }

    public static int getI() {
        return 10;
    }
}
