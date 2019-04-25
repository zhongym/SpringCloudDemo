package com.zym.springcloud.user.center.jdk.jvm;

import java.lang.reflect.Field;

public class LooadDemo {
    static {
        System.out.println("LooadDemo{}");
    }


    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
//        Class<?> aClass = loader.loadClass("com.zym.springcloud.user.center.jdk.jvm.LooadDemo1");
//        System.out.println(aClass);
        LooadDemo1 demo1 = new LooadDemo1();
//        Class<? extends LooadDemo1> aClass = demo1.getClass();
//        Field demo21 = aClass.getDeclaredField("demo2");
//        Class<?> type = demo21.getType();
        demo1.sya2();
        System.out.println(demo1);
    }

}
