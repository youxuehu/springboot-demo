package com.example.springbootdemo.common.db.service.zkdata;

import com.example.springbootdemo.SpringbootDemoApplicationTests;
import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.JobIdGenerator;
import com.example.springbootdemo.utils.ObjectByteConvert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Date;

public class ZkDataServiceTest extends SpringbootDemoApplicationTests {

    @Autowired @Qualifier(value = "zkClientService")
    ZkClientService zkClientService;

    @Test
    public void insert() {
        String jobId = JobIdGenerator.generateJobId();
        String submittedPath = zkClientService.getSubmittedPath(jobId);
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());
        byte[] bytes = ObjectByteConvert.obj2Byte(zkData);
        zkClientService.create(submittedPath, bytes);
    }


    @Test
    public void get() {
        String jobId = JobIdGenerator.generateJobId();
        String submittedPath = zkClientService.getSubmittedPath(jobId);
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());
        byte[] bytes = ObjectByteConvert.obj2Byte(zkData);
        zkClientService.create(submittedPath, bytes);

        Object data = zkClientService.getData(submittedPath);
        System.out.println(data);
    }

}
