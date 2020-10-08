package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.executionrecord.model.ExecutionRecord;

import java.util.List;

public interface ExecutionRecordService {

    void insert(ExecutionRecord executionRecord);

    void updateByPrimaryKey(ExecutionRecord executionRecord);

    ExecutionRecord queryById(Long id);

    List<ExecutionRecord> queryByPage(ExecutionRecord executionRecord, Integer pageIndex, Integer pageSize);

    int queryByCount(ExecutionRecord executionRecord);
}
