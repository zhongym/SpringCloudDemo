package com.zym.springcloud.eureka.server;


public class DateTimeFormatterDemo {
    public static void main(String[] args) {
//        Class<Demo> demoClass = Demo.class;

        try {
            DateTimeFormatterDemo.class.getClassLoader().loadClass("com.zym.springcloud.eureka.server.Demo");
            Class.forName("com.zym.springcloud.eureka.server.Demo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("--------1");
//        Demo demo = Demo.getDemo1();
    }
}
