package org.example.db.service.cleantask.impl;

import org.example.db.dao.cleantask.mapper.CleanTaskMapper;
import org.example.db.dao.cleantask.model.CleanTask;
import org.example.db.dao.cleantask.model.CleanTaskExample;
import org.example.db.service.cleantask.CleanTaskService;

import java.util.List;

public class CleanTaskServiceImpl implements CleanTaskService {
    private CleanTaskMapper cleanTaskMapper;
    @Override
    public Long queryCount() {
        CleanTaskExample condition = new CleanTaskExample();
        return cleanTaskMapper.countByExample(condition);
    }

    @Override
    public List<CleanTask> queryByPage(Integer limit) {
        CleanTaskExample condition = new CleanTaskExample();
        condition.setLimitStart(0);
        condition.setLimitEnd(limit);
        return cleanTaskMapper.selectByExample(condition);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        CleanTaskExample condition = new CleanTaskExample();
        condition.createCriteria().andIdIn(ids);
        return cleanTaskMapper.deleteByExample(condition);
    }

    public void setCleanTaskMapper(CleanTaskMapper cleanTaskMapper) {
        this.cleanTaskMapper = cleanTaskMapper;
    }
}
