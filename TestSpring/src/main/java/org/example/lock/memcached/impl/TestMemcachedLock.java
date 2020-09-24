package org.example.lock.memcached.impl;

public class TestMemcachedLock {

    private MemcachedLock memcachedLock;
    private int count = 0;

    public void execute() {
        try {
            boolean lock = memcachedLock.tryLock();
            if (!lock) {
                System.err.println("<<<<<<<<< 获取锁失败 >>>>>>>>>>");
            }
            System.out.println(++count);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            memcachedLock.releaseLock();
        }

    }

    public void init() {
        while (true) {
            new Thread(){
                @Override
                public void run() {
                    execute();
                }
            }.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setMemcachedLock(MemcachedLock memcachedLock) {
        this.memcachedLock = memcachedLock;
    }
}
