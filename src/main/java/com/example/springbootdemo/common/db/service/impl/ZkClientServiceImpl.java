package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.ObjectByteConvert;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(value = "zkClientService")
public class ZkClientServiceImpl implements ZkClientService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientServiceImpl.class);

    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 15 * 1000;

    //ZooKeeper服务地址
    @Value("${zk_server}")
    private String zkServer;

    @Value("${zk_root}")
    String zkRoot;

    private CuratorFramework client;

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
            client.create().forPath(path, serializable);
        } catch (Exception e) {
            LOGGER.error("create zk node error", e);
        }
    }

    @Override
    public void createWithModel(String path, byte[] serializable, CreateMode createMode) {
        try {
            boolean check = checkExists(path);
            if (check) {
                return;
            }
            client.create().withMode(createMode).forPath(path, serializable);
        } catch (Exception e) {
            LOGGER.error("create zk node error", e);
        }
    }

    @Override
    public boolean checkExists(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            return stat != null ? true : false;
        } catch (Exception e) {
            LOGGER.error("check zk node exists error", e);
        }
        return false;
    }

    @Override
    public List<String> getChildren(String path) {
        try {
            List<String> childrenPath = client.getChildren().forPath(path);
            return CollectionUtils.isEmpty(childrenPath) ? new ArrayList<>() : childrenPath;
        } catch (Exception e) {
            LOGGER.error("get zk children node error", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void setData(String path, byte[] serializable) {
        try {
            client.setData().forPath(path, serializable);
        } catch (Exception e) {
            LOGGER.error("set data zk error", e);
        }
    }

    @Override
    public void delete(String path, boolean force) {
        try {
            if (!checkExists(path)) {
                return;
            }
            if (force) {
                // 级联删除子节点
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
            }
            client.delete().forPath(path);
        } catch (Exception e) {
            LOGGER.error("delete zk node error", e);
        }
    }

    @Override
    public Object getData(String submittedPath) {
        try {
            byte[] bytes = client.getData().forPath(submittedPath);
            Object o = ObjectByteConvert.byte2Obj(bytes);
            return o;
        } catch (Exception e) {
            LOGGER.error("getData zk node error", e);
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //1 重试策略：重试间隔时间为1s 重试1次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //2 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkServer).connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                //命名空间
                .namespace(zkRoot)
                .build();
        //3 开启连接
        client.start();
        this.client = client;
        System.out.println("client state:"+client.getState());
    }
}
