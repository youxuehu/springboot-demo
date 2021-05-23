package com.example.springbootdemo.daemon;

import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.manager.ExecutionContext;
import com.example.springbootdemo.manager.JobManager;
import com.example.springbootdemo.manager.res.ResultLog;
import com.example.springbootdemo.utils.InetAddressUtil;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.example.springbootdemo.utils.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class JobFetcher implements Runnable, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobFetcher.class);

    @Autowired @Qualifier(value = "zkClientService")
    private ZkClientService zkClientService;

    @Autowired
    private JobManager jobManager;

    @Override
    public void run() {
        String assignmentsPath = zkClientService.getAssignmentsPath();
        String taskPath = assignmentsPath + "/" + InetAddressUtil.getLocalHost();
        List<String> jobList = zkClientService.getChildren(taskPath);
        LOGGER.warn("fetch jobs list: {}", jobList);
        jobList.forEach(item -> {
            Object data = zkClientService.getData(taskPath + "/" +item);
            ZkData zkData = ObjectConverter.json2Obj((String) data, ZkData.class);
            Object content = ObjectByteConvert.byte2Obj(zkData.getData());
            // init sender
            ExecutionContext executionContext = new ExecutionContext();
            BlockingQueue<ResultLog> queue = jobManager.getRandomQueue();
            jobManager.initSender(executionContext, queue, zkData.getJobId());
            jobManager.execute(executionContext, (String) content);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        threadPoolExecutor.scheduleAtFixedRate(this, 1, 10, TimeUnit.SECONDS);
    }
}
