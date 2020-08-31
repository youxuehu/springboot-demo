package com.example.springbootdemo.common.builder;

import com.example.springbootdemo.common.builder.job.Job;

import java.util.List;

public interface JobBuilder {

    void invoke(List<Job> jobs);

    String getType();
}
