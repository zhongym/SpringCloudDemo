package com.zym.springcloud.user.center.jdk.jvm;

public class LooadDemo1 /*extends LooadDemo2*/ {
    private LooadDemo2 demo2 = null;

    static {
        System.out.println("LooadDemo1 {}");
    }

    public void sya2() {
        LooadDemo2.sya();
    }

    public LooadDemo2 getDemo2() {
        return demo2;
    }
}
