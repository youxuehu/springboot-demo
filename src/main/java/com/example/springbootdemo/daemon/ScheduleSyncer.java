package com.example.springbootdemo.daemon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.springbootdemo.common.zookeeper.ZkManager;
import com.example.springbootdemo.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleSyncer implements Runnable {

    Logger LOG = LoggerFactory.getLogger(ScheduleSyncer.class);

    ZkManager zkManager;

    public ScheduleSyncer(ZkManager zkManager) {
        this.zkManager = zkManager;
    }

    @Override
    public void run() {
        LOG.info("{}", TimeUtils.currentTime());
        zkManager.createNode();
        LOG.info("zookeeper children node list : {}", JSON.toJSONString(zkManager.getChildren(), SerializerFeature.PrettyFormat));
        String data = zkManager.readData("/aa");
        System.out.println(data);
    }
}
