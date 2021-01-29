package com.example.springbootdemo.handler.job;

public class PythonJob extends AbstractJob implements Job{
    @Override
    public JobType getJobType() {
        return JobType.PYTHON_JOB;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
