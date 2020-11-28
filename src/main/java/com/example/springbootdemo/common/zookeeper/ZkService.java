package com.example.springbootdemo.common.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkLock;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ZkService implements InitializingBean, Watcher {

    private static final Logger LOG = LoggerFactory.getLogger(ZkService.class);

    @Value("${zookeeper.host}")
    private String connection;

    ZkClient zkClient;

    public String createPersistent(String path, Object data){
        try {
            zkClient.createPersistent(path, data);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String createEphemeral(String path, Object data){
        try {
            zkClient.createEphemeral(path, data);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String readNode(String path){
        try {
            String data = zkClient.readData(path, true);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeData(String path, Object data){
        ZkClient zkClient = new ZkClient(connection, 5000);
        try {
            zkClient.writeData(path, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getChild() {
        return zkClient.getChildren("/test_zk");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        creteNode("/node2");
//        createEphemeral("/youxuehu");
//        writeData("/node2");
//        String data = readNode("/node2");
        zkClient = new ZkClient(connection);
        zkClient.setZkSerializer(new MyZkSerializer());
//        zkClient.createPersistent("/bb", "bb");
        LOG.info("~~~~~~~~~~~~~~~~~~~zookeeper connected success ~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.info("zookeeper watch node is {} ", watchedEvent.getPath());
    }
}
