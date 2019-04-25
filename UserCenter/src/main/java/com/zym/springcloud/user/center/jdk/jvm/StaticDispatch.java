package com.zym.springcloud.user.center.jdk.jvm;

public class StaticDispatch {

    static interface I {

    }

    static interface I1 {

    }

    static class C1 implements I, I1 {

    }

    static class C2 implements I {

    }

    public void say(I i) {
        System.out.println("i");
    }
//    public void say(I1 i) {
//        System.out.println("i1");
//    }

//    public void say(C1 c1) {
//        System.out.println("c1");
//    }

    public void say(C2 c1) {
        System.out.println("c2");
    }

    public void say2(int a) {
        System.out.println("int");
    }

    public void say2(long a) {
        System.out.println("long");
    }

    public void testLocalVar(int i, long l) {
        int a = 20;

        if (a < 20) {
            long b = 20;
        } else {
            long b = 20;
            long g = 30;
        }

        long d = 30;

        i = a;
        l = d;
    }

    public static void main(String[] args) {
        C1 c1 = new C1();
        I c2 = new C2();

        StaticDispatch dispatch = new StaticDispatch();
        dispatch.say(c1);
        dispatch.say(c2);

        char c = 'a';
        dispatch.say2(c);
    }
}
