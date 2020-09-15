package com.example.springbootdemo.common.thread.lock;

import java.util.concurrent.*;

public class ReadWriteLockTest {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2, 0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                    }
                });
    }
}

class MyClass {
    private int a;

    /**
     * 读取操作
     * @return
     */
    public int getA() {
        return a;
    }

    /**
     * 写入操作
     * @param a
     */
    public void setA(int a) {
        this.a = a;
    }
}