package com.example.springbootdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.service.ExecutionService;
import com.example.springbootdemo.service.TestSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestSpringServiceImpl implements TestSpringService {
    @Autowired
    private ExecutionService executionService;
    @Override
    public String sayHello(String name) {
        Map<String, Object> json = new HashMap<>();
        String result = executionService.execute(JSON.toJSONString(json));
        return "name: " + name + " result" + result;
    }
}
