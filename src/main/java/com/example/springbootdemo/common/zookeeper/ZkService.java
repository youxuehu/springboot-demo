package com.example.springbootdemo.common.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkLock;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZkService implements InitializingBean, Watcher {

    private static final Logger LOG = LoggerFactory.getLogger(ZkService.class);

    @Value("${connection}")
    private String connection;

    public String creteNode(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            zkClient.createPersistent(path, "1111");
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String createEphemeral(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            zkClient.createEphemeral(path, "2222");
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String readNode(String path){
        ZkClient zkClient = new ZkClient(connection);
        try {
            String data = zkClient.readData(path, true);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeData(String path){
        ZkClient zkClient = new ZkClient(connection, 5000);
        try {
            zkClient.writeData(path, "haha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        creteNode("/node2");
//        createEphemeral("/youxuehu");
        writeData("/node2");
        String data = readNode("/node2");
        LOG.info("~~~~~~~~~~~~~~~~~~~zookeeper node info data is {} ~~~~~~~~~~~~~~~~~~~~~~~~", data);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.info("zookeeper watch node is {} ", watchedEvent.getPath());
    }
}
