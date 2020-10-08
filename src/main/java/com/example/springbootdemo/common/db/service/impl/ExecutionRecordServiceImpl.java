package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.dao.executionrecord.mapper.ExecutionRecordMapper;
import com.example.springbootdemo.common.db.dao.executionrecord.model.ExecutionRecord;
import com.example.springbootdemo.common.db.dao.executionrecord.model.ExecutionRecordExample;
import com.example.springbootdemo.common.db.service.ExecutionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExecutionRecordServiceImpl implements ExecutionRecordService {

    @Autowired
    ExecutionRecordMapper executionRecordMapper;

    @Override
    public void insert(ExecutionRecord executionRecord) {
        executionRecord.setGmtCreate(new Date());
        executionRecord.setGmtUpdate(new Date());
        executionRecord.setIsDeleted(false);
        executionRecordMapper.insertSelective(executionRecord);
    }

    @Override
    public void updateByPrimaryKey(ExecutionRecord executionRecord) {
        executionRecordMapper.updateByPrimaryKeySelective(executionRecord);
    }

    @Override
    public ExecutionRecord queryById(Long id) {
        return executionRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExecutionRecord> queryByPage(ExecutionRecord executionRecord, Integer pageIndex, Integer pageSize) {
        ExecutionRecordExample condition = new ExecutionRecordExample();
        int offSet = pageIndex == 1 ? pageSize * (pageIndex - 1) : pageSize * (pageIndex - 1) + 1;
        condition.setOrderByClause("id desc");
        condition.setLimitStart(offSet);
        condition.setLimitEnd(pageSize);
        return executionRecordMapper.selectByExample(condition);
    }

    @Override
    public int queryByCount(ExecutionRecord executionRecord) {
        ExecutionRecordExample condition = new ExecutionRecordExample();
        return executionRecordMapper.countByExample(condition);
    }
}
