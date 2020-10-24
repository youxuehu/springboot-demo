package com.example.springbootdemo.common.设计模式.生产者与消费者;

/**
 * 生产者与消费者模式
 */
public class ProducterConsumerTest {

    public static void main(String[] args) {

        new ProducterConsumerTest().execute();
    }

    private void execute() {
        MyQueue myQueue = new MyQueue();
        new Thread(new Producter(myQueue)).start();
        new Thread(new Consumer(myQueue)).start();
    }

    private class Producter implements Runnable {
        private MyQueue myQueue;

        public Producter(MyQueue myQueue) {
            this.myQueue = myQueue;
        }

        @Override
        public void run() {
            for (char c  = 'A'; c <= 'Z'; c++) {
                myQueue.push(c);
            }
        }
    }
    private class Consumer implements Runnable {
        private MyQueue myQueue;

        public Consumer(MyQueue myQueue) {
            this.myQueue = myQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 26; i ++) {
                myQueue.pop();
            }
        }
    }
    private class MyQueue {
        private int[] arrays = new int[6];
        int count = 0;
        public synchronized void push(int num) {
            while(arrays.length == count) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            arrays[count] = num;
            System.out.println("push >>> " + num + "   count >>>>>>" + count);
            count++;
            this.notifyAll();
        }
        public synchronized void pop() {
            while(count == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("pop >>> " + arrays[0] + "   count >>>>>>" + count);
            for (int i = 0; i < count - 1; i++) {
                arrays[i] = arrays[i + 1];
            }
            count--;
            this.notifyAll();
        }
    }
}
