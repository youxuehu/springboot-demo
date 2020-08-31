package com.example.springbootdemo.common.builder.proxy.impl;

import com.example.springbootdemo.common.builder.enums.JobAlgoType;
import com.example.springbootdemo.common.builder.JobBuilder;
import com.example.springbootdemo.common.builder.proxy.JobBuilderProxy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JobBuilderProxyImpl implements JobBuilderProxy {
    private Map<String, JobBuilder> jobBuilderMap = new ConcurrentHashMap<>();
    @Override
    public void register(String jobAlgoType, JobBuilder jobBuilder) {
        synchronized (this) {
            if (!jobBuilderMap.containsKey(jobAlgoType)) {
                jobBuilderMap.put(jobAlgoType, jobBuilder);
            }
        }
    }

    @Override
    public List<JobBuilder> getJobBuilderList() {
        List<JobBuilder> jobBuildersList = new ArrayList<>();
        for (Map.Entry entry : jobBuilderMap.entrySet()) {
            jobBuildersList.add((JobBuilder) entry.getValue());
        }
        return jobBuildersList;
    }

    @Override
    public JobBuilder getJobBuilder(JobAlgoType jobAlgoType) {
        if (jobBuilderMap.containsKey(jobAlgoType.name())) {
            return jobBuilderMap.get(jobAlgoType.name());
        }
        return null;
    }
}
