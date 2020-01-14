package com.zym.springcloud.user.center.jdk.jvm.javac;

public class ThisDemo {


    public void test1() {
        System.out.println("adfa");
    }

    public void test3() {
       this.test1();
    }


    public class InnerClass {

        public void test() {
            test1();
        }

        public void test2() {
            ThisDemo.this.test1();
        }
    }

    public static void main(String[] args) {

        InnerClass innerClass = new ThisDemo().new InnerClass();
    }


}
