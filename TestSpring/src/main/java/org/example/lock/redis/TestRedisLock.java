package org.example.lock.redis;

import org.example.lock.LockService;

public class TestRedisLock {

    private LockService lockService;

    private int count = 0;

    public void execute() {
        long lock = lockService.tryLock("LOCK_KEY");
        if (lock == 0) {
            System.out.println("获取分布式锁失败");
            return;
        }
        try {
            System.out.println(++count);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lockService.unlock("LOCK_KEY", lock);
        }

    }

    public void init() {
        while (true) {
            new Thread() {
                @Override
                public void run() {
                    execute();
                }
            }.start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }
}
