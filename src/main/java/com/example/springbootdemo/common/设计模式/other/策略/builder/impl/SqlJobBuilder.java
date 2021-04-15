package com.example.springbootdemo.common.设计模式.other.策略.builder.impl;

import com.example.springbootdemo.common.设计模式.other.策略.builder.enums.JobAlgoType;
import com.example.springbootdemo.common.设计模式.other.策略.builder.AbstractJobBuilder;
import com.example.springbootdemo.common.设计模式.other.策略.builder.JobBuilder;
import com.example.springbootdemo.common.设计模式.other.策略.builder.job.Job;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SqlJobBuilder extends AbstractJobBuilder implements JobBuilder {

    @Override
    public void invoke(List<Job> jobs) {

    }

    @Override
    public String getType() {
        return JobAlgoType.SQL.name();
    }

}
