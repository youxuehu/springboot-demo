package com.example.springbootdemo.common.zookeeper;

import com.example.common.db.service.zk.ZkClientService;
import com.example.common.utils.InetAddressUtil;
import com.example.common.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class ZkManager implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(ZkManager.class);

    @Autowired
    private ZkService zkService;

    @Autowired @Qualifier(value = "zkClientService")
    private ZkClientService zkClientService;

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

    public List<String> getAssignments() {
        String assignmentsPath = zkClientService.getZkPath4Assignments();
        String localHost = InetAddressUtil.getLocalHost();
        String myJobPath = assignmentsPath + "/" + localHost;
        List<String> jobIds = zkClientService.ls(myJobPath);
        LOG.warn("当前代执行的job: {}", jobIds);
        return jobIds;
    }
}
