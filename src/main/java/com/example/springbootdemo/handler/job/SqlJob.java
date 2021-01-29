package com.example.springbootdemo.handler.job;

public class SqlJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.SQL_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
