//package com.zym.springcloud.user.center.guava;
//
//import sun.reflect.CallerSensitive;
//import sun.reflect.Reflection;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
//
//public class AtomicDemo {
//
//    public static void main(String[] args) {
//
//        A a = A.getA();
//
//        AtomicBoolean b = new AtomicBoolean(false);
//        boolean andSet = b.getAndSet(true);
//
//
//        AtomicIntegerFieldUpdater<A> i = AtomicIntegerFieldUpdater.newUpdater(A.class, "i");
//        i.addAndGet(new A(),1);
//
//        Executors.newWorkStealingPool(10);
//
//
//    }
//
//    public static class A{
//
//        @CallerSensitive
//        public static A getA(){
//            Class<?> callerClass = Reflection.getCallerClass();
//            System.out.println(callerClass);
//            return new A();
//        }
//
//        private Integer i;
//
//        public Integer getI() {
//            return i;
//        }
//
//        public void setI(Integer i) {
//            this.i = i;
//        }
//    }
//}
