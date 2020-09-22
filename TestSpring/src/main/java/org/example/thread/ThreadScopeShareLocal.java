package org.example.thread;

import java.util.Random;

public class ThreadScopeShareLocal {

    private static int data = 0;

    public static void main(String[] args) {

        for (int i = 0; i< 2;i++) {
            new Thread() {
                @Override
                public void run() {
                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " put data:" + data);
                    new A().get(data);
                    new B().get(data);
                }
            }.start();
        }
    }


}

class A {
    public void get(int data) {
        System.out.println("A from "+Thread.currentThread().getName() +"get data:" +data);
    }
}
class B {
    public void get(int data) {
        System.out.println("B from "+Thread.currentThread().getName() +"get data:" +data);
    }
}