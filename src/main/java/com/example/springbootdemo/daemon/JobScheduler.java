package com.example.springbootdemo.daemon;

import com.example.springbootdemo.common.db.dao.worker.model.Worker;
import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class JobScheduler implements Runnable, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Autowired @Qualifier(value = "zkClientService")
    private ZkClientService zkClientService;

    @Override
    public void run() {
        String submittedPath = zkClientService.getSubmittedPath();
        List<String> submittedJobList = zkClientService.getChildren(submittedPath);
        List<ZkData> zkDataList = new ArrayList<>();
        submittedJobList.forEach(item -> {
            Object data = zkClientService.getData(submittedPath +"/"+item);
            if (data != null) {
                ZkData zkData = ObjectConverter.json2Obj((String) data, ZkData.class);
                zkDataList.add(zkData);
            }
        });
        LOGGER.warn("已提交列表的任务个数: {}", zkDataList.size());

        // 查询服务正常的worker
        String heartBeatsPath = zkClientService.getHeartBeatsPath();
        List<String> heartBeatsList = zkClientService.getChildren(heartBeatsPath);
        List<Worker> workers = new ArrayList<>();
        heartBeatsList.forEach(item -> {
            Object data = zkClientService.getData(heartBeatsPath+"/"+item);
            if (data != null) {
                Worker worker = ObjectConverter.json2Obj((String) data, Worker.class);
                workers.add(worker);
            }
        });
        LOGGER.warn("服务正常的worker: {}", workers.stream().map(Worker::getHost).collect(Collectors.toList()));
        // 选择空闲的把任务分配给它
        String assignmentsPath = zkClientService.getAssignmentsPath();
        for (ZkData zkData : zkDataList) {
            String host = getMaxFreeMemory(workers);
            zkClientService.create(assignmentsPath + "/" + host + "/" + zkData.getJobId(),  zkData.getData());
        }

        // 从已提交的列表删除任务
        for (String path : submittedJobList) {
            zkClientService.delete(submittedPath + "/" + path, true);
        }

    }

    private String getMaxFreeMemory(List<Worker> workers) {
        // 获取内存free 多的内存
        Optional<Worker> maxWorker = workers.stream().max(Comparator.comparingLong(Worker::getFreeMemory));
        Worker worker = maxWorker.get();
        String host = worker.getHost();
        return host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, 10, 9, TimeUnit.SECONDS);
    }
}
