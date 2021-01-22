package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.service.ExecutionService;
import org.springframework.stereotype.Service;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    @Override
    public String execute(String param) {
        return param;
    }
}
