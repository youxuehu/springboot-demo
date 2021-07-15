package com.example.springbootdemo.handler.impl;

import com.example.common.db.dao.executionlog.model.ExecutionLog;
import com.example.common.db.service.executionlog.ExecutionLogService;
import com.example.springbootdemo.manager.res.ResultLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDbLogHandler {

    @Autowired
    ExecutionLogService executionLogService;

    public void handler(List<ResultLog> logs) {
        logs.forEach(log -> {
            ExecutionLog executionLog = new ExecutionLog();
            executionLog.setContent(log.getContent());
            executionLog.setGmtCreate(log.getGmtCreate());
            executionLog.setJobId(log.getJobId());
            executionLogService.insert(executionLog);
        });
    }
}
