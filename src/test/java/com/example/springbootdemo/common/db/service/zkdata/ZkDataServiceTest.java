package com.example.springbootdemo.common.db.service.zkdata;

import com.example.common.db.dao.zkdata.model.ZkData;
import com.example.common.db.service.zk.ZkClientService;
import com.example.common.utils.JobIdGenerator;
import com.example.common.utils.ObjectByteConvert;
import com.example.common.utils.ObjectConverter;
import com.example.springbootdemo.SpringbootDemoApplicationTests;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Date;

public class ZkDataServiceTest extends SpringbootDemoApplicationTests {

    @Autowired
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
        zkClientService.create(submittedPath, zkData, CreateMode.EPHEMERAL);
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
        zkClientService.create(submittedPath, zkData, CreateMode.PERSISTENT);

        ZkData data = zkClientService.getData(submittedPath, ZkData.class);
        System.out.println(ObjectConverter.obj2Json(data));
    }

}
