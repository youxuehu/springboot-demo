package com.example.springbootdemo.common.quartz.componment;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job{
    void execute(JobExecutionContext context) throws JobExecutionException;
}