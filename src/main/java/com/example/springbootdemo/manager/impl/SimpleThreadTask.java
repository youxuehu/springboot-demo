package com.example.springbootdemo.manager.impl;

import com.example.springbootdemo.handler.impl.SaveDbLogHandler;
import com.example.springbootdemo.manager.res.ResultLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SimpleThreadTask implements Runnable {
    private BlockingQueue<ResultLog> queue;
    private SaveDbLogHandler saveDbLogHandler;
    private static final Integer LOG_COUNT_LIMIT = 400;
    public SimpleThreadTask(BlockingQueue<ResultLog> queue, SaveDbLogHandler saveDbLogHandler) {
        this.queue = queue;
        this.saveDbLogHandler = saveDbLogHandler;
    }

    @Override
    public void run() {
        if (queue.size() == 0) {
            return;
        }
        List<ResultLog> logs = new ArrayList<>();
        queue.drainTo(logs, LOG_COUNT_LIMIT);
        saveDbLogHandler.handler(logs);
    }
}
