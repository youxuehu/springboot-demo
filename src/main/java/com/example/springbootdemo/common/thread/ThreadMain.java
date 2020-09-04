package com.example.springbootdemo.common.thread;

public class ThreadMain {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + ( i + 1));
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + ( i + 1));
        }
    }
}