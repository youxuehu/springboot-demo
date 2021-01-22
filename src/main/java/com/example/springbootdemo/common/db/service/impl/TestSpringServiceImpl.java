package com.example.springbootdemo.common.db.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common.db.service.ExecutionService;
import com.example.springbootdemo.common.db.service.TestSpringService;
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
        json.put("name", "游学虎");
        json.put("age", 20);
        String result = executionService.execute(JSON.toJSONString(json));
        return "name: " + name + " result" + result;
    }
}
