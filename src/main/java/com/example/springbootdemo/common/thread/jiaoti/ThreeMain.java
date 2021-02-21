package com.example.springbootdemo.common.thread.jiaoti;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程ABC交替执行
 * 逻辑：AB线程交替打印1～100，当打印的值是10的倍数时，让C线程打印
 */
public class ThreeMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeMain.class);

    public static void main(String[] args) {
        new ThreeMain().execute();
    }

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

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
                        business.c1(atomicInteger.incrementAndGet());
                        business.setSelect("B");
                        c2.signal();
                        if (atomicInteger.get() >= 100) break;

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

                        if (atomicInteger.get() % 10 != 0) {
                            business.c2(atomicInteger.incrementAndGet());
                        }
                        business.setSelect("C");
                        c3.signal();
                        if (atomicInteger.get() >= 100) break;
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
                        if (atomicInteger.get() == 100) break;
                        while (!StringUtils.equals(business.select, "C")) {
                            try {
                                c3.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (atomicInteger.get() % 10 == 0) {
                            business.c3(atomicInteger.get());
                        }
                        business.setSelect("A");
                        c1.signal();
                        if (atomicInteger.get() >= 100) break;
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

        public void c1(int a) {

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicInteger.get() < 100) {
                LOGGER.info("A线程打印 >>>>> " + a);
            }

        }
        public void c2(int b) {
            if (atomicInteger.get() == 100) return;
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (b % 10 == 0) {
                return;
            }
            LOGGER.info("B线程打印 >>>>> " + b);
        }
        public void c3(int c) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("C线程打印 >>>>> " + c);
        }
    }
}
