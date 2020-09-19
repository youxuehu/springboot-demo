package com.example.springbootdemo.common.builder.impl;

import com.example.springbootdemo.common.builder.AbstractJobBuilder;
import com.example.springbootdemo.common.builder.enums.JobAlgoType;
import com.example.springbootdemo.common.builder.JobBuilder;
import com.example.springbootdemo.common.builder.job.Job;
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