package com.example.springbootdemo.handler.job;

public class StopJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.STOP_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
