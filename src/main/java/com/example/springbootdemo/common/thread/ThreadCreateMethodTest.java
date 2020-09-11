package com.example.springbootdemo.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadCreateMethodTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });


    }
}
