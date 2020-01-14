package com.zym.springcloud.user.center.jdk.jvm;

import java.util.ArrayList;
import java.util.List;

public class InitDemo2 {

    private List<String> list;

    {
        list.add("132");
    }


    private InitDemo2() {
        list = new ArrayList<>();

    }

    private void test2(InitDemo2 i) {
        System.out.println(this);
    }


    private static void test(InitDemo2 i){
//        i = new InitDemo2();
       i.list = new ArrayList<>();
    }

    public static void main(String[] args) {
        InitDemo2 i = new InitDemo2();
        test(i);
        System.out.println(i);


//        new InitDemo2();
//
//        HashMap<String, String> hashMap = new HashMap<String, String>(-1, 1) {
//            {
//                put("key", "value");
//                System.out.println("");
//            }
//        };
    }
}
