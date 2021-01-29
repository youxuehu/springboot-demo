package com.example.springbootdemo.handler.job;

public class YarnJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.YARN_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
