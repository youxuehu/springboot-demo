package com.example.springbootdemo.daemon;

import com.alibaba.fastjson.JSON;
import com.example.common.db.dao.worker.model.Worker;
import com.example.common.db.service.zk.ZkClientService;
import com.example.common.utils.ObjectByteConvert;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class JobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    @Autowired
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
                String submittedPath = zkClientService.getZkPath4SubmittedJobs();
                List<String> submittedJobList = zkClientService.ls(submittedPath);
                List<Submit> submits = new ArrayList<>();
                if (CollectionUtils.isEmpty(submittedJobList)) {
                    LOGGER.warn("已提交列表的任务是空");
                    return;
                }
                for (String item : submittedJobList) {
                    Submit submit = zkClientService.getData(submittedPath +"/"+item, Submit.class);
                    if (submit != null) {
                        submits.add(submit);
                    }
                }
                LOGGER.warn("已提交列表的任务个数： {} , 任务列表：{}", submits.size(), JSON.toJSONString(submits, true));

                // 查询服务正常的worker
                String heartBeatsPath = zkClientService.getZkPath4Workers();
                List<String> heartBeatsList = zkClientService.ls(heartBeatsPath);
                List<Worker> workers = new ArrayList<>();
                heartBeatsList.forEach(item -> {
                    Worker worker = zkClientService.getData(heartBeatsPath + "/" + item, Worker.class);
                    if (worker != null) {
                        workers.add(worker);
                    }
                });
                LOGGER.warn("服务正常的worker: {}", workers.stream().map(Worker::getHost).collect(Collectors.toList()));
                // 选择空闲的把任务分配给它
                String assignmentsPath = zkClientService.getZkPath4Assignments();
                for (Submit submit : submits) {
                    String host = getMaxFreeMemory(workers);
                    if (!zkClientService.exists(assignmentsPath + "/" + host)) {
                        zkClientService.create(assignmentsPath + "/" + host, 1, CreateMode.PERSISTENT);
                    }
                    zkClientService.create(
                            assignmentsPath + "/" + host + "/" + submit.getJob().getJobId(),
                            submit,
                            CreateMode.PERSISTENT);
                }

                // 从已提交的列表删除任务
                for (String path : submittedJobList) {
                    zkClientService.delete(submittedPath + "/" + path);
                }
            } catch (Exception e) {
                LOGGER.warn("", e);
            }
        }

}
