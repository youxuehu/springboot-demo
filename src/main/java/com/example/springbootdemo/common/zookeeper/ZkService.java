package com.example.springbootdemo.common.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkLock;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZkService implements InitializingBean {

    @Value("${connection}")
    private String connection;

    public String creteNode(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            zkClient.createPersistent(path);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String createEphemeral(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            zkClient.createEphemeral(path);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String lock(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            ZkLock eventLock = zkClient.getEventLock();
            eventLock.lock();
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        creteNode("/node2");
        createEphemeral("/youxuehu");
    }
}
