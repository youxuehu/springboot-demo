package com.example.springbootdemo.handler.job;

public class LaunchContrainerJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.LAUNCH_CONTAINER;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
