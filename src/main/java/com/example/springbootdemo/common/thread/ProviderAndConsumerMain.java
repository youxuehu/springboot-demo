package com.example.springbootdemo.common.thread;

import java.util.Arrays;

/**
 * 生产者与消费者模式
 */
public class ProviderAndConsumerMain {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        // 2个生产者
        Provider p = new Provider(queue);
        Provider p1 = new Provider(queue);
        // 2个消费者
        Consumer c = new Consumer(queue);
        Consumer c1 = new Consumer(queue);

        new Thread(p).start();
        new Thread(p1).start();
        new Thread(c).start();
        new Thread(c1).start();


    }
}
class MyQueue {

    public String[] array = new String[6];

    // array = 6 provider wait

    // array = 0 consumer wait

    int count = 0;

    // add
    public synchronized void push(String num) {
        // 防止多个生产者，生产时出现数组越界
        while (count == array.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        array[count] = num;
        System.out.println("push -- " + num + " -- " + count);
        count++;

        // notify
        this.notifyAll();
    }

    // del
    public synchronized void pop() {
        // 防止多个消费者同时消费，出现数组越界
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("pop -- " + array[0]);
        for (int i = 0; i < count - 1; i++) {
            array[i] = array[i + 1];
        }
        count--;
        this.notifyAll();
    }

}
class Provider implements Runnable {

    private MyQueue queue;

    public Provider(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (char i = 'A'; i <= 'Z'; i++) {
            queue.push(i + "");
        }

        System.out.println("push show array info --- " + Arrays.toString(queue.array));
    }
}

class Consumer implements Runnable {

    private MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 26; i++) {
            queue.pop();
        }
        System.out.println("pop show array info --- " + Arrays.toString(queue.array));
    }
}