package com.example.springbootdemo.handler;

import com.example.springbootdemo.manager.ExecutionContext;

public interface JobHandler {

    void invoke(ExecutionContext executionContext, String content);

    TaskTypeEnum getType();
}
