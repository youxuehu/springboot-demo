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
public class Thread4 implements Runnable {

    private Lock lock;
    private Condition condition1;
    private Condition condition4;
    private AtomicInteger atomicInteger;

    public Thread4(Lock lock, Condition condition1, Condition condition4, AtomicInteger atomicInteger) {
        this.lock = lock;
        this.condition1 = condition1;
        this.condition4 = condition4;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 25; i++) {
                while (ThreadEnum.THREAD4 != ThreadConstant.threadEnum) {
                    try {
                        condition4.await();
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
                ThreadConstant.threadEnum = ThreadEnum.THREAD1;
                condition1.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
