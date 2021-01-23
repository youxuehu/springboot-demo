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
        try {
            String ymd = TimeUtils.ymd();
            String path = logRootPath + "/" + ymd;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String filePath = path+ "/" +jobId + ".log";
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (this.loggerPrint == null) {
                this.loggerPrint = new PrintWriter(new FileWriter(file));
            }
        } catch (IOException e) {
            LOGGER.warn("new PrintWriter", e);
        }
    }

    public void send(ResultLog log) {
        if (log != null) {
            queue.add(log);
        }
        loggerPrint.println(log);
        loggerPrint.flush();
    }
}
