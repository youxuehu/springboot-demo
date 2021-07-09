package com.example.springbootdemo.common.thread.MultiThread.FourThread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author youxuehu
 * @version v1.0
 * @className Thread1
 * @date 2021/7/9 10:12 下午
 * @desrription 这是类的描述信息
 */
public class Thread2 implements Runnable {

    private Lock lock;
    private Condition condition2;
    private Condition condition3;
    private AtomicInteger atomicInteger;

    public Thread2(Lock lock, Condition condition2, Condition condition3, AtomicInteger atomicInteger) {
        this.lock = lock;
        this.condition2 = condition2;
        this.condition3 = condition3;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 25; i++) {
                while (ThreadEnum.THREAD2 != ThreadConstant.threadEnum) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "------->" + atomicInteger.getAndIncrement());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadConstant.threadEnum = ThreadEnum.THREAD3;
                condition3.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
