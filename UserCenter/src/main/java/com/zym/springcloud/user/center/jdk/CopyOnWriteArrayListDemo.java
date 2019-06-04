package com.zym.springcloud.user.center.jdk;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public A getA(){
        return new A();
    }

    private class A{

        public int a;

        public void  say(){

        }
    }

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("");


    }
}
