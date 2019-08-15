package com.zym.springcloud.user.center.jdk;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static final Singleton INSTANCE = new Singleton();

    private String a = new String(new char[]{'c'});

    private Singleton() {
        System.out.println("Singleton()");
    }
    {
        System.out.println("{}");
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
