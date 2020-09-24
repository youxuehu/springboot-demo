package org.example.lock.memcached.impl;

public class TestMemcachedLock {

    private MemcachedLock memcachedLock;

    public void execute() {
        try {
            boolean lock = memcachedLock.tryLock();
            if (!lock) {
                System.err.println("<<<<<<<<< 获取锁失败 >>>>>>>>>>");
                return;
            }
            for (int i = 0; i < "zhangsan".length(); i++) {
                char c = "zhangsan".charAt(i);
                System.out.print(c);
            }
            System.out.println();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            memcachedLock.releaseLock();
        }

    }

    public void init() {
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    execute();
                }
            }.start();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setMemcachedLock(MemcachedLock memcachedLock) {
        this.memcachedLock = memcachedLock;
    }
}
