package com.example.springbootdemo.daemon;

import com.example.springbootdemo.common.zookeeper.ZkManager;
import com.example.springbootdemo.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class WorkerSyncer implements InitializingBean {

    Logger LOG = LoggerFactory.getLogger(WorkerSyncer.class);

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Autowired
    ZkManager zkManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("线程定时调度开始启动, 当前时间：{}", TimeUtils.currentTime());
//        5秒后执行线程
//        executorService.schedule(new ScheduleSyncer(), 5, TimeUnit.SECONDS);
//        1秒后执行线程，以后每隔5秒执行一次线程
        executorService.scheduleAtFixedRate(new ScheduleSyncer(zkManager), 1, 5, TimeUnit.SECONDS);
        LOG.info("线程定时调度启动完成, 当前时间：{}", TimeUtils.currentTime());
    }
}
