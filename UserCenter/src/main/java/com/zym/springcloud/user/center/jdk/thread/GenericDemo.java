package com.zym.springcloud.user.center.jdk.thread;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    static class T {
        void s() {

        }
    }

    static class A extends T {
        void a() {

        }
    }

    static class B extends A {
        void b() {

        }
    }

    static class Test {
        void say(Comparable<? extends A> a) {

        }

        void say1(Comparable<? super A> a) {
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.say(new Comparable<A>() {
            @Override
            public int compareTo(A o) {
                return 0;
            }
        });

        test.say(new Comparable<B>() {
            @Override
            public int compareTo(B o) {
                return 0;
            }
        });

        test.say1(new Comparable<T>() {
            @Override
            public int compareTo(T o) {
                return 0;
            }
        });

        List<B> a = new ArrayList<>();
//        l1(a);
//        l2(a);
        List c = a;
        List<Object> b = c;

        List<B> a1 = a;


        //List<T> 互转 List<Object>
//        List<T> tList = new ArrayList<>();
//        List list = tList;
//        List<Object> oList = list;
//
//        list = oList;
//        tList = list;


        // List<T> 互转 List<?>
        List<T> tList = new ArrayList<>();
        List<?> anyList = tList;
        List list = anyList;
        tList = list;




    }

    //    static void l1(List<?> list){
//        Object o = list.get(0);
//        list.add(new A());
//    }
//    static void l2(List<? extends A> list){
//        A a = list.get(0);
//        list.add(new B());
//    }
    static void l3(List<Object> list) {
        list.add(new A());
    }
}
