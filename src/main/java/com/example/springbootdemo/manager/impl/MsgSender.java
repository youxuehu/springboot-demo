package com.example.springbootdemo.manager.impl;

import com.example.springbootdemo.manager.res.ResultLog;
import com.example.springbootdemo.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class MsgSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgSender.class);

    private BlockingQueue<ResultLog> queue;

    private String logRootPath;

    private PrintWriter loggerPrint;

    public MsgSender(String logRootPath, BlockingQueue<ResultLog> queue, String jobId) {
        this.logRootPath = logRootPath;
        this.queue = queue;
        String ymd = TimeUtils.ymd();
        File file = new File(logRootPath + "/" + ymd + jobId + ".log");
        file.mkdirs();
        try {
            loggerPrint = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            LOGGER.warn("new PrintWriter", e);
        }
    }

    public void send(ResultLog log) {
        if (log != null) {
            queue.add(log);
        }
        loggerPrint.println(log);
    }
}
