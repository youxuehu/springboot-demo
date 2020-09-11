package com.example.springbootdemo.common.thread.CountDownTest;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 等待指定的线程执行完毕，才开始执行
 * <<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  Thread-0
 * <<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  Thread-2
 * <<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  Thread-1
 * <<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  Thread-3
 * <<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  Thread-4
 * ～～～～～～～～～～～开始执行主线程～～～～～～～～～～～～
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecuteTask executeTask = new ExecuteTask(countDownLatch);
        executeTask.start();

        ExecuteTask executeTask2 = new ExecuteTask(countDownLatch);
        executeTask2.start();


        ExecuteTask executeTask3 = new ExecuteTask(countDownLatch);
        executeTask3.start();

        ExecuteTask executeTask4 = new ExecuteTask(countDownLatch);
        executeTask4.start();


        ExecuteTask executeTask5 = new ExecuteTask(countDownLatch);
        executeTask5.start();

        countDownLatch.await();

        System.out.println("～～～～～～～～～～～开始执行主线程～～～～～～～～～～～～");
    }

    static class ExecuteTask extends Thread {
        private CountDownLatch countDownLatch;

        public ExecuteTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("<<<<<<<<<<<<<<<< task execute success >>>>>>>>>>>>>>>>  " + Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }
}
