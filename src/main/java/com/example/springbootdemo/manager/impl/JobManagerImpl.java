package com.example.springbootdemo.manager.impl;

import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.handler.JobHandler;
import com.example.springbootdemo.handler.JobHandlerFactory;
import com.example.springbootdemo.handler.TaskTypeEnum;
import com.example.springbootdemo.handler.impl.SaveDbLogHandler;
import com.example.springbootdemo.manager.ExecutionContext;
import com.example.springbootdemo.manager.JobManager;
import com.example.springbootdemo.manager.res.ResultLog;
import com.example.springbootdemo.utils.JobIdGenerator;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Service
public class JobManagerImpl implements JobManager, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobManagerImpl.class);

    @Value("${log_root_path}")
    private String logRootPath;

    private ScheduledExecutorService threadPool;
    private static final Integer MESSAGE_LOG_COUNT = 4;

    @Autowired
    private SaveDbLogHandler saveDbLogHandler;

    @Autowired
    private ZkClientService zkClientService;

    protected List<BlockingQueue<ResultLog>> queues = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        threadPool = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException("Task " + r.toString() +
                        " rejected from " + executor.toString());
            }
        });
        for (int i = 0; i < MESSAGE_LOG_COUNT; i++) {
            BlockingQueue<ResultLog> queue = new LinkedBlockingQueue<>();
            queues.add(queue);
            threadPool.scheduleAtFixedRate(new SimpleThreadTask(queue, saveDbLogHandler), 50, 50, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public String submit(String content) {
        String jobId = JobIdGenerator.generateJobId();
        //execute(executionContext, content);
        zkClientService.create(zkClientService.getSubmittedPath() + "/" + jobId, ObjectByteConvert.obj2Byte(content));
        return jobId;
    }

    @Override
    public String execute(ExecutionContext executionContext, String content) {
        List<JobHandler> jobHandlers = JobHandlerFactory.getJobHandlers(Lists.newArrayList(TaskTypeEnum.SINGLETON));
        for (JobHandler jobHandler : jobHandlers) {
            jobHandler.invoke(executionContext, content);
        }
        return null;
    }

    @Override
    public BlockingQueue<ResultLog> getRandomQueue() {
        return queues.get(new Random().nextInt(queues.size()));
    }

    @Override
    public void initSender(ExecutionContext executionContext, BlockingQueue<ResultLog> queue, String jobId) {
        MsgSender sender = new MsgSender(logRootPath, queue, jobId);
        executionContext.setMsgSender(sender);
        executionContext.setJobId(jobId);
    }
}
