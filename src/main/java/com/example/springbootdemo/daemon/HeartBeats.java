package com.example.springbootdemo.daemon;

import com.example.springbootdemo.common.db.dao.worker.model.Worker;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.InetAddressUtil;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.example.springbootdemo.utils.ObjectConverter;
import com.sun.management.OperatingSystemMXBean;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class HeartBeats implements Runnable, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeats.class);

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Autowired @Qualifier(value = "zkClientService")
    ZkClientService zkClientService;

    @Override
    public void run() {
        String localHost = InetAddressUtil.getLocalHost();
        String heartBeatsPath = zkClientService.getHeartBeatsPath() + "/" + localHost;
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Worker worker = new Worker();
        worker.setHost(localHost);
        worker.setMemory(mem.getTotalPhysicalMemorySize());
        worker.setFreeMemory(mem.getFreePhysicalMemorySize());
        worker.setJobCount(0L);
        zkClientService.createWithModel(heartBeatsPath, ObjectByteConvert.obj2Byte(ObjectConverter.obj2Json(worker)), CreateMode.EPHEMERAL);
        LOGGER.warn("HeartBeats task alive , localHost: {}, worker info : {}", localHost, ObjectConverter.obj2Json(worker));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, 10, 10, TimeUnit.SECONDS);
    }
}
