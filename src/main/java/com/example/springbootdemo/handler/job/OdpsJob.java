package com.example.springbootdemo.handler.job;

public class OdpsJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.ODPS_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
