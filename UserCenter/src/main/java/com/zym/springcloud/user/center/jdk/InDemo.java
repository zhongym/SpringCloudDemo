package com.zym.springcloud.user.center.jdk;

import java.util.ArrayList;

public class InDemo extends ArrayList {

    public static void main(String[] args) {
        I i = null;
        boolean equals = new Object().equals(i);
        System.out.println(equals);

    }

}
