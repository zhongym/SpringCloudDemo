package com.zym.springcloud.user.center.jdk;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put(new A(1), new A(2));
        map.put(new A(2), new A(2));
        System.out.println(map);

    }

    public static class A implements Comparable<A> {
        private int a;

        public A(int a) {
            this.a = a;
        }

        @Override
        public int compareTo(A o) {
            return Integer.compare(o.a, a);
        }
    }
}
