package org.example.lock;

import org.example.db.dao.bizlock.model.BizLock;
import org.example.db.service.bizlock.BizLockService;

import java.util.Date;

public class DistributeLock {

    private BizLockService bizLockService;

    /**
       create table if not exists biz_lock(id bigint primary key auto_increment, biz_id varchar(255), biz_type varchar(255),
        lock_type varchar(255), lock_time datetime, version int);
     */
    public Boolean tryLock(BizLock bizLock) {
        int version = 0;
        String bizId = bizLock.getBizId();
        String bizType = bizLock.getBizType();
        String lockType = bizLock.getLockType();
        BizLock bizLockTarget = new BizLock();
        bizLockTarget.setVersion(version + 1);
        bizLockTarget.setLockTime(new Date());
        int count = bizLockService.updateByBizIdAndVersionAndBizTypeAndLoclType(version, bizId, bizType,
                lockType, bizLockTarget);
        return count == 1;
    }

    public Boolean releaseLock(BizLock bizLock) {
        int version = 1;
        String bizId = bizLock.getBizId();
        String bizType = bizLock.getBizType();
        String lockType = bizLock.getLockType();
        BizLock bizLockTarget = new BizLock();
        bizLockTarget.setVersion(version - 1);
        bizLockTarget.setLockTime(new Date());
        int count = bizLockService.updateByBizIdAndVersionAndBizTypeAndLoclType(version, bizId, bizType,
                lockType, bizLockTarget);
        return count == 1;
    }

    public void setBizLockService(BizLockService bizLockService) {
        this.bizLockService = bizLockService;
    }
}
