package com.zym.springcloud.user.center.jdk;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhongym
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        // create and start threads
        for (int i = 0; i < N; ++i) {
            Thread thread = new Thread(new Worker(startSignal, doneSignal));
            thread.start();
        }
        doSomethingElse();            // don't let run yet
        startSignal.countDown();
        // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }

    private static void doSomethingElse() {
        System.out.println("doSomethingElse");
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                startSignal.await();
                doWork();

            } catch (InterruptedException ex) {

            } finally {

                doneSignal.countDown();
            }
        }

        void doWork() {
            System.out.println(Thread.currentThread().getName() + ":doWork()");
        }
    }
}
