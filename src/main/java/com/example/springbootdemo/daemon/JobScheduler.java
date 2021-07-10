package com.example.springbootdemo.daemon;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common.db.dao.worker.model.Worker;
import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.example.springbootdemo.utils.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spire.math.algebraic.Sub;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class JobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    @Autowired @Qualifier(value = "zkClientService")
    private ZkClientService zkClientService;

    private String getMaxFreeMemory(List<Worker> workers) {
        // 获取内存free 多的内存
        Optional<Worker> maxWorker = workers.stream().max(Comparator.comparingLong(Worker::getFreeMemory));
        Worker worker = maxWorker.get();
        String host = worker.getHost();
        return host;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
//        Thread thread = new Thread(new ScheduleTask());
//        thread.setDaemon(true);
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(thread, 1, 10, TimeUnit.SECONDS);
//    }

        @Scheduled(fixedRate = 3000)
        public void run() {
            try {
                LOGGER.warn("任务调度开始");
                String submittedPath = zkClientService.getSubmittedPath();
                List<String> submittedJobList = zkClientService.getChildren(submittedPath);
                List<Submit> submits = new ArrayList<>();

                for (String item : submittedJobList) {
                    Object data = zkClientService.getData(submittedPath +"/"+item);
                    if (data != null) {
                        Submit submit = ObjectConverter.json2Obj((String) data, Submit.class);
                        submits.add(submit);
                    }
                }
                LOGGER.warn("已提交列表的任务个数: {}", submits.size());

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
                for (Submit submit : submits) {
                    String host = getMaxFreeMemory(workers);
                    if (!zkClientService.checkExists(assignmentsPath + "/" + host)) {
                        zkClientService.create(assignmentsPath + "/" + host, "1".getBytes(StandardCharsets.UTF_8));
                    }
                    zkClientService.create(assignmentsPath + "/" + host + "/" + submit.getJob().getJobId(),  ObjectByteConvert.obj2Byte(JSON.toJSONString(submit)));
                }

                // 从已提交的列表删除任务
                for (String path : submittedJobList) {
                    zkClientService.delete(submittedPath + "/" + path, true);
                }
            } catch (Exception e) {
                LOGGER.warn("", e);
            }
        }

}
