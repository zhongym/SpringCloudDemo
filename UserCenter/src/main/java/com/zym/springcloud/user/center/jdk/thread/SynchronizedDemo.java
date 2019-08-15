package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized无法禁止指令重排序的证明
 */
public class SynchronizedDemo {
    private static volatile int i = 0;
    private static volatile int j = 0;
        
    public static void main(String[] args) {
        Runnable thread1  = new Runnable() {
            
            @Override
            public void run() {
                while(true) {
                    selfPlus();
                }
            }
        };
        
        Runnable thread2 = new Runnable() {
            
            @Override
            public void run() {
                while(true) {
                    print();
                }
            }
        };
        
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(thread1);
        pool.execute(thread2);
        pool.shutdown();
    }
    
    public static synchronized void selfPlus() {
//        if(i != j)
//        System.out.println("selfPlus: i= "+i+" ; j= "+j);
        i++;
        j++;
//        if(i != j)
//        System.out.println("selfPlus: i= "+i+" ; j= "+j);
    }
    
    public static void print(){
//        if(i<j)
//            System.out.println("i= "+i+" ; j= "+j);

        if(j >i)
        System.out.println("i= "+i+" ; j= "+j);
    }

}