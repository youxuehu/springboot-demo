package com.example.springbootdemo.common.thread.jiaoti;

public class TwoJiaotiMain {

    public static void main(String[] args) {
        new TwoJiaotiMain().execute();
    }

    private void execute() {
        Business business = new Business();
        new Thread(){
            @Override
            public void run() {
                for (int i = 2; i <= 100 ; i = i + 2) {
                    business.sub(i);
                }
            }
        }.start();
        for (int i = 1; i <= 100 ; i = i + 2) {
            business.main(i);
        }
    }

    private class Business {
        boolean flag = true;
        public synchronized void main(int i) {
            while (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " >>>>>> " + i);
            flag = false;
            this.notifyAll();
        }
        public synchronized void sub(int i) {
            while (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " >>>>>> " + i);
            flag = true;
            this.notifyAll();
        }
    }
}
