package com.example.springbootdemo.manager;

import com.example.springbootdemo.manager.res.ResultLog;

import java.util.concurrent.BlockingQueue;

public interface JobManager {

    String submit(String content);

    String execute(ExecutionContext executionContext, String content);

    BlockingQueue<ResultLog> getRandomQueue();

    void initSender(ExecutionContext executionContext, BlockingQueue<ResultLog> queue, String jobId);
}
