//package com.zym.springcloud.user.center.jdk.优雅退出;
//
//import sun.misc.Signal;
//import sun.misc.SignalHandler;
//
//@Deprecated
//public class SignalDemo {
//    public static void main(String[] args) {
//        Signal signal = new Signal("TERM");
//        Signal.handle(signal, new SignalHandler() {
//            @Override
//            public void handle(Signal signal) {
//                int number = signal.getNumber();
//                if (number == 0) {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("释放资源完成，退出");
//                }
//            }
//        });
//        System.out.println("退出");
//        System.exit(0);
//    }
//}
