package com.example.springbootdemo.handler.job;

public class ShellJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.SHELL_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
