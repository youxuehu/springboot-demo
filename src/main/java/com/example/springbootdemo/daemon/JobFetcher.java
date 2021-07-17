package com.example.springbootdemo.daemon;

import com.example.common.db.service.zk.ZkClientService;
import com.example.common.utils.InetAddressUtil;
import com.example.springbootdemo.manager.ExecutionContext;
import com.example.springbootdemo.manager.JobManager;
import com.example.springbootdemo.manager.res.ResultLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
@EnableScheduling
public class JobFetcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobFetcher.class);

    @Autowired @Qualifier(value = "zkClientService")
    private ZkClientService zkClientService;

    @Autowired
    private JobManager jobManager;

    @Scheduled(fixedRate = 3000)
    public void run() {
        List<String> jobList = null;
        String taskPath = null;
        try {
            String assignmentsPath = zkClientService.getZkPath4Assignments();
            taskPath = assignmentsPath + "/" + InetAddressUtil.getLocalHost();
            jobList = zkClientService.ls(taskPath);
            LOGGER.warn("fetch jobs list: {}", jobList);
            final String finalTaskPath = taskPath;
            jobList.forEach(item -> {
                Submit submit = zkClientService.getData(finalTaskPath + "/" +item, Submit.class);
//                Object content = ObjectByteConvert.byte2Obj(zkData.getData());
                // init sender
                ExecutionContext executionContext = new ExecutionContext();
                BlockingQueue<ResultLog> queue = jobManager.getRandomQueue();
                jobManager.initSender(executionContext, queue, submit.getJob().getJobId());
                jobManager.execute(executionContext, submit.getJob().getContent());
            });
        } catch (Exception e) {
            LOGGER.warn("", e);
        } finally {
            if (!CollectionUtils.isEmpty(jobList)) {
                for (String jobId : jobList) {
                    zkClientService.delete(taskPath + "/" + jobId);
                }
            }
        }
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
//        threadPoolExecutor.scheduleAtFixedRate(this, 1, 10, TimeUnit.SECONDS);
//    }
}
