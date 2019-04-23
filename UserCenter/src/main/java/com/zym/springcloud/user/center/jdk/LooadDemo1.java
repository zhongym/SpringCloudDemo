package com.zym.springcloud.user.center.jdk;

public class LooadDemo1 {
    static {
        System.out.println("LooadDemo1 {}");
    }

    public void sya2(){
        LooadDemo2 demo2 = new LooadDemo2();
        demo2.sya2();
    }
}
