package com.example.springbootdemo.daemon;

import com.alibaba.fastjson.JSON;
import com.example.common.db.dao.worker.model.Worker;
import com.example.common.db.service.zk.ZkClientService;
import com.example.common.utils.InetAddressUtil;
import com.example.common.utils.ObjectByteConvert;
import com.example.common.utils.ObjectConverter;
import com.sun.management.OperatingSystemMXBean;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component
@EnableScheduling
public class HeartBeats {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeats.class);

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Autowired
    ZkClientService zkClientService;

    @Scheduled(fixedRate = 3000)
    public void run() {
        String localHost = InetAddressUtil.getLocalHost();
        String heartBeatsPath = zkClientService.getZkPath4Workers() + "/" + localHost;
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Worker worker = new Worker();
        worker.setHost(localHost);
        worker.setMemory(mem.getTotalPhysicalMemorySize());
        worker.setFreeMemory(mem.getFreePhysicalMemorySize());
        worker.setJobCount(0L);
        zkClientService.create(heartBeatsPath, worker, CreateMode.PERSISTENT);
        LOGGER.warn("HeartBeats task alive , localHost: {}, worker info : {}",
                localHost, JSON.toJSONString(worker, true));
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, 10, 10, TimeUnit.SECONDS);
//    }
}
