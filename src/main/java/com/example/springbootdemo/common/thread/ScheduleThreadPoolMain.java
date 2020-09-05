package com.example.springbootdemo.common.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolMain {

    public static void main(String[] args) {

        /// jdk1.5开始， 支持多线程的任务调度
        /**
         *     public ScheduledThreadPoolExecutor(int corePoolSize) {
         *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue());
         *     }
         */
        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
//        ScheduledThreadPoolExecutor schedule2 = new ScheduledThreadPoolExecutor(1);
        ScheduledThreadPoolExecutor schedule3 = new ScheduledThreadPoolExecutor(1);
//        ScheduledThreadPoolExecutor schedule4 = new ScheduledThreadPoolExecutor(1);

//        // 每5秒钟执行一次
//        schedule.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }, 0, 5, TimeUnit.SECONDS);



        MyQueue queue = new MyQueue();
        // 2个生产者
        Provider p = new Provider(queue);
//        Provider p1 = new Provider(queue);
        // 2个消费者
        Consumer c = new Consumer(queue);
//        Consumer c1 = new Consumer(queue);

        // 每5秒钟执行一次
        schedule.scheduleWithFixedDelay(p, 0, 5, TimeUnit.SECONDS);
//        schedule2.scheduleWithFixedDelay(p1, 0, 5, TimeUnit.SECONDS);
        schedule3.scheduleWithFixedDelay(c, 0, 5, TimeUnit.SECONDS);
//        schedule4.scheduleWithFixedDelay(c1, 0, 5, TimeUnit.SECONDS);

    }
}
