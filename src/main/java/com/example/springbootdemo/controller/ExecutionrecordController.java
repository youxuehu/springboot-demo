package com.example.springbootdemo.controller;

import com.example.common.db.service.executionrecord.ExecutionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExecutionrecordController {
    @Autowired
    ExecutionRecordService executionRecordService;
    @RequestMapping("/execRecprd/all")
    @ResponseBody
    public Object execRecordAll() {
        return executionRecordService.queryByPage(null, 1, 100);
    }
}
