package com.example.springbootdemo.service.executionrecord;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.SpringbootDemoApplicationTests;
import com.example.springbootdemo.common.db.dao.executionrecord.model.ExecutionRecord;
import com.example.springbootdemo.common.db.service.ExecutionRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.springbootdemo.utils.GenerateChineseName.getChineseName;

public class ExecutionRecordServiceTest extends SpringbootDemoApplicationTests {

    @Autowired
    ExecutionRecordService executionRecordService;

    @Test
    public void queryPage() {
        List<ExecutionRecord> executionRecords = executionRecordService.queryByPage(null, 1, 10);
        System.out.println(JSON.toJSONString(executionRecords, true));
    }

    @Test
    public void insert() {
        for (int i = 0; i < 100; i++) {
            ExecutionRecord executionRecord = new ExecutionRecord();
            executionRecord.setUserName(getChineseName());
            executionRecord.setUserNumber("WB" + (int)((Math.random()*9+1)*100000));
            executionRecord.setStatus(1);
            executionRecord.setType(6);
            executionRecord.setExperimentType("GUI");
            executionRecordService.insert(executionRecord);
        }
    }

}
