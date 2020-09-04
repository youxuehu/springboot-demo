package com.example.springbootdemo.common.thread;

/**
 * 生产者与消费者模式
 */
public class ProviderAndConsumerMain {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        Provider p = new Provider(queue);
        Provider p1 = new Provider(queue);
        Consumer c = new Consumer(queue);

        new Thread(p).start();
        new Thread(p1).start();
        new Thread(c).start();


    }
}
class MyQueue {

    String[] array = new String[6];

    // array = 6 provider wait

    // array = 0 consumer wait

    int count = 0;

    // add
    public synchronized void push(String num) {
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
        while(true) {
            for (char i = 'A'; i <= 'Z'; i++) {
                queue.push(i + "");
            }
        }
    }
}

class Consumer implements Runnable {

    private MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            for (int i = 0; i <= 26; i++) {
                queue.pop();
            }
        }
    }
}