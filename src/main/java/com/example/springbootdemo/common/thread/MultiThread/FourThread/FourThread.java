package com.example.springbootdemo.common.thread.MultiThread.FourThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author youxuehu
 * @version v1.0
 * @className FourThread
 * @date 2021/7/9 10:11 下午
 * @desrription 这是类的描述信息
 */
public class FourThread {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        Condition condition4 = lock.newCondition();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4, 4, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4));

        threadPoolExecutor.execute(new Thread1(lock, condition1, condition2, atomicInteger));
        threadPoolExecutor.execute(new Thread2(lock, condition2, condition3, atomicInteger));
        threadPoolExecutor.execute(new Thread3(lock, condition3, condition4, atomicInteger));
        threadPoolExecutor.execute(new Thread4(lock, condition1, condition4, atomicInteger));
    }
}
