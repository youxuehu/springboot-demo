package com.example.springbootdemo.common.builder.impl;

import com.example.springbootdemo.common.builder.AbstractJobBuilder;
import com.example.springbootdemo.common.builder.enums.JobAlgoType;
import com.example.springbootdemo.common.builder.JobBuilder;
import com.example.springbootdemo.common.builder.job.Job;
import com.example.springbootdemo.common.builder.proxy.JobBuilderProxy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteBookJobBuilder extends AbstractJobBuilder implements JobBuilder, InitializingBean {
    @Autowired
    JobBuilderProxy jobBuilderProxy;
    @Override
    public void invoke(List<Job> jobs) {

    }

    @Override
    public String getType() {
        return JobAlgoType.NOTEBOOK.name();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jobBuilderProxy.register(getType(), this);
    }
}
