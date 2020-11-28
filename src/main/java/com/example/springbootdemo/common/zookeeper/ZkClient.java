package com.example.springbootdemo.common.zookeeper;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class ZkClient {

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.newClient("master:2181,slave1:2181,slave2:2181", retryPolicy);
        curatorFramework.start();

        CuratorZookeeperClient zookeeperClient = curatorFramework.getZookeeperClient();
        ZooKeeper zooKeeper = zookeeperClient.getZooKeeper();
        long sessionId = zooKeeper.getSessionId();
        System.out.println(sessionId);
        List<String> children = zooKeeper.getChildren("/", null);
        System.out.println(children);
    }
}
