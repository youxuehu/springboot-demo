package com.example.springbootdemo.daemon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.common.db.service.zk.ZkClientService;
import com.example.springbootdemo.common.zookeeper.ZkManager;
import com.example.springbootdemo.handler.JobHandler;
import com.example.springbootdemo.handler.JobHandlerFactory;
import com.example.springbootdemo.handler.TaskTypeEnum;
import com.example.springbootdemo.manager.ExecutionContext;
import com.example.springbootdemo.utils.InetAddressUtil;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class ScheduleSyncer implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleSyncer.class);

    private ZkClientService zkClientService;

    public ScheduleSyncer(ZkClientService zkClientService) {
        this.zkClientService = zkClientService;
    }

    @Override
    public void run() {
//        List<Job> jobs = new ArrayList<>();
//        List<String> jobIds = zkManager.getAssignments();
//        for (String jobId : jobIds) {
//            String myData = zkManager.readData(jobId);
//            Job job = JSON.parseObject(myData, new TypeReference<Job>() {
//            });
//            jobs.add(job);
//        }
//
//        for (Job job : jobs) {
//            TaskTypeEnum taskTypeEnum = job.getTaskTypeEnum();
//            JobHandler jobHandler = JobHandlerFactory.getJobHandler(taskTypeEnum);
//            if (jobHandler == null) {
//                continue;
//            }
//            jobHandler.invoke(new ExecutionContext(), job.getContent());
//        }
        String assignmentsPath = zkClientService.getZkPath4Assignments();
        String taskPath = assignmentsPath + "/" + InetAddressUtil.getLocalHost();
        zkClientService.create(taskPath, "11".getBytes(), CreateMode.EPHEMERAL);
    }
}
