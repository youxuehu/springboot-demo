package com.example.springbootdemo.common.设计模式.策略.builder.proxy;

import com.example.springbootdemo.common.设计模式.策略.builder.JobBuilder;
import com.example.springbootdemo.common.设计模式.策略.builder.enums.JobAlgoType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobBuilderProxyFactory {
    private static Map<String, JobBuilder> jobBuilderMap = new ConcurrentHashMap<>();
    public static void register(JobBuilder jobBuilder) {
        synchronized (JobBuilderProxyFactory.class) {
            if (!jobBuilderMap.containsKey(jobBuilder.getType())) {
                jobBuilderMap.put(jobBuilder.getType(), jobBuilder);
            }
        }
    }

    public static List<JobBuilder> getJobBuilderList() {
        List<JobBuilder> jobBuildersList = new ArrayList<>();
        for (Map.Entry entry : jobBuilderMap.entrySet()) {
            jobBuildersList.add((JobBuilder) entry.getValue());
        }
        return jobBuildersList;
    }

    public static JobBuilder getJobBuilder(JobAlgoType jobAlgoType) {
        if (jobBuilderMap.containsKey(jobAlgoType.name())) {
            return jobBuilderMap.get(jobAlgoType.name());
        }
        return null;
    }
}
