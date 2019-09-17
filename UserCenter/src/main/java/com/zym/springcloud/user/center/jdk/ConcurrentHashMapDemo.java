package com.zym.springcloud.user.center.jdk;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    {
        System.out.println("{}");

        CopyOnWriteArrayListDemo d = new CopyOnWriteArrayListDemo();
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

//        ConcurrentHashMapDemo concurrentHashMapDemo = new ConcurrentHashMapDemo();

        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        Long l1 = 521461L;
        Long l2 = 521461L;

        Long absent = map.putIfAbsent(l1, l1);
        Long aLong = absent == null ? map.get(l1) : absent;
        Long aLong1 = map.putIfAbsent(l2, l2);
        System.out.println(aLong);

    }
}
