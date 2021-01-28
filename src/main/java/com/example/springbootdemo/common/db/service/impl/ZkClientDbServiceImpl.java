package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.dao.zkdata.mapper.ZkDataMapper;
import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service(value = "zkClientDb")
public class ZkClientDbServiceImpl implements ZkClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientDbServiceImpl.class);

    @Value("${zk_root}")
    String zkRoot;

    @Autowired
    private ZkDataMapper zkDataMapper;

    @Override
    public String getSubmittedPath() {
        return "/submitted_jobs";
    }

    @Override
    public String getHeartBeatsPath() {
        return "/heartbeats";
    }

    @Override
    public String getAssignmentsPath() {
        return "/assignments";
    }

    @Override
    public void create(String path, byte[] serializable) {
        try {
            ZkData zkData = new ZkData();
            zkData.setRoot(zkRoot);
            zkData.setPath(path);
            zkData.setData(serializable);
            zkData.setGmtCreate(new Date());
            zkDataMapper.insertSelective(zkData);
        } catch (Exception e) {
            LOGGER.error("create zk path error", e);
        }
    }

    @Override
    public void createWithModel(String path, byte[] serializable, CreateMode createMode) {

    }

    @Override
    public boolean checkExists(String path) {
        return false;
    }

    @Override
    public List<String> getChildren(String path) {
        return null;
    }

    @Override
    public void setData(String path, byte[] serializable) {

    }

    @Override
    public void delete(String path, boolean force) {

    }

    @Override
    public Object getData(String submittedPath) {
        return null;
    }
}
