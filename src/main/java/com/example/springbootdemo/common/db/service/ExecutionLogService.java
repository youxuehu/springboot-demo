package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.executionlog.model.ExecutionLog;

import java.util.List;

public interface ExecutionLogService {

    void insert(ExecutionLog executionLog);

    List<ExecutionLog> queryLogsByJobIds(String jobId);

}
