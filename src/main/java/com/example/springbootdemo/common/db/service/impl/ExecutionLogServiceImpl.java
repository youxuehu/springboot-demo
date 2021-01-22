package com.example.springbootdemo.common.db.service.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.example.springbootdemo.common.db.dao.executionlog.mapper.ExecutionLogMapper;
import com.example.springbootdemo.common.db.dao.executionlog.model.ExecutionLog;
import com.example.springbootdemo.common.db.dao.executionlog.model.ExecutionLogExample;
import com.example.springbootdemo.common.db.service.ExecutionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutionLogServiceImpl implements ExecutionLogService {

    @Autowired
    ExecutionLogMapper executionLogMapper;

    @Override
    public void insert(ExecutionLog executionLog) {
        executionLogMapper.insertSelective(executionLog);
    }

    @Override
    public List<ExecutionLog> queryLogsByJobIds(String jobId) {
        ExecutionLogExample condition = new ExecutionLogExample();
        condition.createCriteria().andJobIdEqualTo(jobId);
        List<ExecutionLog> executionLogs = executionLogMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(executionLogs) ? new ArrayList<>() : executionLogs;
    }
}
