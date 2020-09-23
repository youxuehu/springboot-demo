package org.example.db.service.bizlock;

import org.example.db.dao.bizlock.model.BizLock;

public interface BizLockService {

    int updateByBizIdAndVersionAndBizTypeAndLoclType(int version, String bizId,
                                                         String bizType, String lockType,
                                                         BizLock bizLock);

    void delete();


    void insertSlector(BizLock defaultlock);
}
