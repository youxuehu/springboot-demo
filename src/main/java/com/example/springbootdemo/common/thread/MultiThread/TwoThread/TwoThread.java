package com.example.springbootdemo.common.thread.MultiThread.TwoThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author youxuehu
 * @version v1.0
 * @className TwoThread
 * @date 2021/7/9 9:58 下午
 * @desrription 这是类的描述信息
 */
public class TwoThread {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 2, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        Business business = new Business();
        threadPoolExecutor.execute(() -> {
            for (int i = 1; i <= 99; i+=2) {
                business.thread1(i);
            }
        });
        threadPoolExecutor.execute(() -> {
            for (int i = 2; i <= 100; i+=2) {
                business.thread2(i);
            }
        });
        threadPoolExecutor.shutdown();
    }
}
