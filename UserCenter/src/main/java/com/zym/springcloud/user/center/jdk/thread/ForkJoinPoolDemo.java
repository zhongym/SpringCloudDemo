package com.zym.springcloud.user.center.jdk.thread;

import java.util.concurrent.*;

public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(32);
        SumTask task = new SumTask(1, 100000);
        Future<Long> result = pool.submit(task);
        System.out.println(result.get());

        // 线程最终调用ForkJoinTask.doExec()
        // 然后调用抽象的exec()
        // 用户需要实现exec()方法，在方法里面判断进行任务拆分
        //
    }

    private static class SumTask extends RecursiveTask<Long> {
        /**
         * 每个任务需要计算的数值大小
         */
        private int taskSize = 100;
        private int start;
        private int end;
        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            //任务足够小，直接计算
            if ((end - start) <= taskSize) {
                System.out.println(Thread.currentThread() + ":执行-" + start + "..." + end);
                long sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            //拆分成两个任务
            int middle = (start + end) / 2;
            SumTask subTask1 = new SumTask(start, middle);
            SumTask subTask2 = new SumTask(middle + 1, end);

            //执行任务
            invokeAll(subTask1,subTask2);

            //获取子任务的结果聚合
            return subTask1.join() + subTask2.join();
        }
    }
}
