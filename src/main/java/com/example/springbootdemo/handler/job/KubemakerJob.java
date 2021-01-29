package com.example.springbootdemo.handler.job;

public class KubemakerJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.KUBEMAKER_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
