package com.example.springbootdemo.common.thread;

import java.util.Iterator;
import java.util.concurrent.*;

public class ThreadPoolMain {

    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPoolSize = 1000;
        long keepAliveTime = 3600L;

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejected error");
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime , TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(256), threadFactory,
                rejectedExecutionHandler
        );

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread " +
                        Thread.currentThread().getName() + " data processing ....");
            }
        };

        while (true) {
            threadPoolExecutor.execute(runnable);
            int activeCount = threadPoolExecutor.getActiveCount();
            long taskCount = threadPoolExecutor.getTaskCount();
            long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            System.out.println("activeCount = " + activeCount);
            System.out.println("taskCount = " + taskCount);
            System.out.println("completedTaskCount = " + completedTaskCount);
            BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
            Iterator<Runnable> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Runnable next = iterator.next();
                System.out.println(next);
            }
        }
    }
}
