package com.example.springbootdemo.common.db.service;

import org.apache.zookeeper.CreateMode;

import java.io.Serializable;
import java.util.List;

public interface ZkClientService {

    String getSubmittedPath(String jobId);

    void create(String path, byte[] serializable);

    void createWithModel(String path, byte[] serializable, CreateMode createMode);

    boolean checkExists(String path);

    List<String> getChildren(String path);

    void setData(String path, byte[] serializable);

    void delete(String path, boolean force);

    Object getData(String submittedPath);

}
