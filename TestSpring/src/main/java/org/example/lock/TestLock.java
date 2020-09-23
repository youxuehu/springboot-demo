package org.example.lock;

import org.example.db.dao.bizlock.model.BizLock;

public class TestLock {

    private DistributeLock distributeLock;

    static final BizLock DEFAULTLOCK;

    static {
        DEFAULTLOCK = new BizLock();
        DEFAULTLOCK.setVersion(0);
        DEFAULTLOCK.setBizId(BizEnum.BIZID.name());
        DEFAULTLOCK.setBizType(BizEnum.BIZTYPE.name());
        DEFAULTLOCK.setLockType(BizEnum.LOCKTYPE.name());

    }

    enum BizEnum {
        BIZID, BIZTYPE, LOCKTYPE
    }

    int count = 0;

    public void execute() {
        Boolean lock = distributeLock.tryLock(DEFAULTLOCK);
        if (!lock) {
            System.out.println(Thread.currentThread().getName() + " <<<<<<<<<< 获取锁失败 >>>>>>>>>>>");
            return;
        }

        try {
            System.out.println(Thread.currentThread().getName() + " <<<<<<<<<< 获取锁成功 >>>>>>>>>>>");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(++count);
        } finally {
            distributeLock.releaseLock(DEFAULTLOCK);
        }
    }

    public void initLock() {

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(){
                @Override
                public void run() {
                    execute();
                }
            }.start();
        }
    }

    public void setDistributeLock(DistributeLock distributeLock) {
        this.distributeLock = distributeLock;
    }
}
