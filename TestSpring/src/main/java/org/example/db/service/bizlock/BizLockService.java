package org.example.db.service.bizlock;

import org.example.db.dao.bizlock.model.BizLock;

import java.util.List;

public interface BizLockService {

    int updateByBizIdAndVersionAndBizTypeAndLoclType(int version, String bizId,
                                                         String bizType, String lockType,
                                                         BizLock bizLock);

    void deleteByIds(List<Long> ids);

    void insertSlector(BizLock defaultlock);

    List<BizLock> queryByBizIdAndBizTypeAndLockType(String bizId, String bizType, String lockType);
}
