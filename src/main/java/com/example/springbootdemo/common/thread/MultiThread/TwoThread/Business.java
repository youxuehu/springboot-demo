package com.example.springbootdemo.common.thread.MultiThread.TwoThread;

/**
 * @author youxuehu
 * @version v1.0
 * @className Business
 * @date 2021/7/9 10:04 下午
 * @desrription 这是类的描述信息
 */
public class Business {

    private Boolean isWait = false;

    public synchronized void thread1(int value) {
        while (isWait) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "----->" + value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isWait = true;
        this.notify();
    }

    public synchronized void thread2(int value) {
        while (!isWait) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "----->" + value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isWait = false;
        this.notify();
    }
}
