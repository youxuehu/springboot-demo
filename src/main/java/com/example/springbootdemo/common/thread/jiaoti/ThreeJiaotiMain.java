package com.example.springbootdemo.common.thread.jiaoti;

import com.alibaba.druid.util.StringUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序交替执行
 */
public class ThreeJiaotiMain {

    public static void main(String[] args) {
        new ThreeJiaotiMain().execute();
    }

    private void execute() {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        String select = "A";
        Business business = new Business(select);
        new Thread(){
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (true) {
                        while (!StringUtils.equals(business.select, "A")) {
                            try {
                                c1.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        business.c1();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        business.setSelect("B");
                        c2.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lock.unlock();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (true) {
                        while (!StringUtils.equals(business.select, "B")) {
                            try {
                                c2.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        business.c2();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        business.setSelect("C");
                        c3.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lock.unlock();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (true) {
                        while (!StringUtils.equals(business.select, "C")) {
                            try {
                                c3.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        business.c3();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        business.setSelect("A");
                        c1.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lock.unlock();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }

    private class Business {
        private String select;

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        public Business(String select) {
            this.select = select;
        }

        public void c1() {
            System.out.println(Thread.currentThread().getName() + " >>>>>> A");
        }
        public void c2() {
            System.out.println(Thread.currentThread().getName() + " >>>>>> B");
        }
        public void c3() {
            System.out.println(Thread.currentThread().getName() + " >>>>>> C");
        }
    }
}
