package com.zym.springcloud.user.center.jdk;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    {
        System.out.println("{}");
    }

    public ConcurrentHashMapDemo() {
        System.out.println("()");
    }

    public static void main(String[] args) throws Exception {
//        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(4);
//
//        Field field = map.getClass().getDeclaredField("table");
//        field.setAccessible(true);
//        Object v = field.get(map);
//        map.put("a","a");
//        v = field.get(map);
//        map.put("a1","a1");
//        v = field.get(map);
//        map.size();

//        ConcurrentHashMap<String,String> premiumPhone = new ConcurrentHashMap<>();
//        premiumPhone.put("Apple", "iPhone");
//        premiumPhone.put("HTC", "HTC one");
//        premiumPhone.put("Samsung","S5");
//
//        Iterator<String> iterator = premiumPhone.keySet().iterator();
//
//        while (iterator.hasNext())
//        {
//            System.out.println(premiumPhone.get(iterator.next()));
//            premiumPhone.put("Sony", "Xperia Z");
//        }

        ConcurrentHashMapDemo concurrentHashMapDemo = new ConcurrentHashMapDemo();

    }
}
