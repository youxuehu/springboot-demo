package com.example.springbootdemo.common.db.service.zkdata;

import com.example.common.db.dao.zkdata.model.ZkData;
import com.example.common.db.service.zk.ZkClientService;
import com.example.springbootdemo.SpringbootDemoApplicationTests;
import com.example.springbootdemo.utils.JobIdGenerator;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.example.springbootdemo.utils.ObjectConverter;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class ZkDataServiceDBTest extends SpringbootDemoApplicationTests {

    @Autowired @Qualifier(value = "zkClientDb")
    ZkClientService zkClientService;

    @Test
    public void insert() {
        String jobId = JobIdGenerator.generateJobId();
        String submittedPath = zkClientService.getZkPath4SubmittedJobs();
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());
        byte[] bytes = ObjectByteConvert.obj2Byte(zkData);
        zkData.setData(bytes);
        zkClientService.create(submittedPath, bytes, CreateMode.PERSISTENT);
    }


    @Test
    public void get() {
        String jobId = JobIdGenerator.generateJobId();
        String submittedPath = zkClientService.getZkPath4SubmittedJobs();
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());

        byte[] bytes = ObjectByteConvert.obj2Byte(ObjectConverter.obj2Json(zkData));
        zkData.setData(bytes);
        zkClientService.create(submittedPath, bytes, CreateMode.PERSISTENT);

        ZkData data = zkClientService.getData(submittedPath, ZkData.class);
        System.out.println(ObjectConverter.obj2Json(data));
    }

}
