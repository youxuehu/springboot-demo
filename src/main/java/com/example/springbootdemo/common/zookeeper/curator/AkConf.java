package com.example.springbootdemo.common.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkConf {

    @Value("${connection}")
    private String connection;

    @Bean
    public CuratorFramework curatorFramework() {
        // 重试时间1000，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 配置客户端
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connection, retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }
}
