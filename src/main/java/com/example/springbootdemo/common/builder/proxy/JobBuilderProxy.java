package com.example.springbootdemo.common.builder.proxy;

import com.example.springbootdemo.common.builder.enums.JobAlgoType;
import com.example.springbootdemo.common.builder.JobBuilder;
import java.util.List;

public interface JobBuilderProxy {

    void register(String jobAlgoType, JobBuilder jobBuilder);

    List<JobBuilder> getJobBuilderList();

    JobBuilder getJobBuilder(JobAlgoType jobAlgoType);
}
