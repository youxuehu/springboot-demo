package com.example.springbootdemo.common.zookeeper;

import com.example.springbootdemo.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class ZkManager implements InitializingBean {

    Logger LOG = LoggerFactory.getLogger(ZkManager.class);

//    @Autowired
    ZkService zkService;

    public void createNode() {
        String path = "/test_zk/" + TimeUtils.currentTimeTrnn();
        zkService.createPersistent(path, TimeUtils.currentTime());
        LOG.info("create zk node successed, path is {}", path);
    }

    public List<String> getChildren() {
        return zkService.getChild();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String path = "/test_zk/" + TimeUtils.currentTimeTrnn();
        zkService.createPersistent(path, TimeUtils.currentTime());
        LOG.info("create zk node successed, path is {}", path);
    }

    public String readData(String s) {
        return zkService.readNode(s);
    }
}
