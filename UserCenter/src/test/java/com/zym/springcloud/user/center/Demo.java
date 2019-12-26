package com.zym.springcloud.user.center;

import java.util.ArrayList;
import java.util.List;

public class Demo<T> {

    public void a(T t){

    }

    public T b(){
        return null;
    }


    public static void main(String[] args) {

        List<?> aList = new ArrayList<>();
//        aList.add()

        List<Object> oList = new ArrayList<>();
        oList.add(new Demo());
        Object o = oList.get(0);

        List<Demo> dList = new ArrayList<>();
        Demo demo = dList.get(0);

        Demo<?> objectDemo = new Demo<>();
        Object b = objectDemo.b();

    }

    private static void run() {

    }
}
