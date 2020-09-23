package org.example.db.service.bizlock.impl;

import org.example.db.dao.bizlock.mapper.BizLockMapper;
import org.example.db.dao.bizlock.model.BizLock;
import org.example.db.dao.bizlock.model.BizLockExample;
import org.example.db.service.bizlock.BizLockService;

public class BizLockServiceImpl implements BizLockService {

    private BizLockMapper bizLockMapper;

    @Override
    public int updateByBizIdAndVersionAndBizTypeAndLoclType(int version, String bizId,
                                                                String bizType, String lockType,
                                                                BizLock bizLock) {
        BizLockExample condition = new BizLockExample();
        condition.createCriteria().andVersionEqualTo(version).andBizIdEqualTo(bizId)
                .andBizTypeEqualTo(bizType).andLockTypeEqualTo(lockType);
        return bizLockMapper.updateByExampleSelective(bizLock, condition);
    }

    @Override
    public void delete() {
        bizLockMapper.deleteByExample(new BizLockExample());
    }

    @Override
    public void insertSlector(BizLock defaultlock) {
        bizLockMapper.insertSelective(defaultlock);
    }

    public void setBizLockMapper(BizLockMapper bizLockMapper) {
        this.bizLockMapper = bizLockMapper;
    }
}
