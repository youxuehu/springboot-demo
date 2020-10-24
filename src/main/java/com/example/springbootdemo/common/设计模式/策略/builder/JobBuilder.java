package com.example.springbootdemo.common.设计模式.策略.builder;

import com.example.springbootdemo.common.设计模式.策略.builder.job.Job;

import java.util.List;

public interface JobBuilder {

    void invoke(List<Job> jobs);

    String getType();
}
