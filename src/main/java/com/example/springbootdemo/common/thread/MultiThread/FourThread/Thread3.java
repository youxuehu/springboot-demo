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
public class Thread3 implements Runnable {

    private Lock lock;
    private Condition condition3;
    private Condition condition4;
    private AtomicInteger atomicInteger;

    public Thread3(Lock lock, Condition condition3, Condition condition4, AtomicInteger atomicInteger) {
        this.lock = lock;
        this.condition3 = condition3;
        this.condition4 = condition4;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 25; i++) {
                while (ThreadEnum.THREAD3 != ThreadConstant.threadEnum) {
                    try {
                        condition3.await();
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
                ThreadConstant.threadEnum = ThreadEnum.THREAD4;
                condition4.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
