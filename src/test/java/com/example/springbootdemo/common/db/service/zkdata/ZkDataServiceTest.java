package com.example.springbootdemo.common.db.service.zkdata;

import com.example.springbootdemo.SpringbootDemoApplicationTests;
import com.example.springbootdemo.common.db.dao.zkdata.model.ZkData;
import com.example.springbootdemo.common.db.service.ZkClientService;
import com.example.springbootdemo.utils.JobIdGenerator;
import com.example.springbootdemo.utils.ObjectByteConvert;
import com.example.springbootdemo.utils.ObjectConverter;
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
        String submittedPath = zkClientService.getSubmittedPath();
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());
        byte[] bytes = ObjectByteConvert.obj2Byte(zkData);
        zkData.setData(bytes);
        zkClientService.create(submittedPath, bytes);
    }


    @Test
    public void get() {
        String jobId = JobIdGenerator.generateJobId();
        String submittedPath = zkClientService.getSubmittedPath();
        ZkData zkData = new ZkData();
        zkData.setId(1L);
        zkData.setRoot("/root");
        zkData.setPath("/submitted_jobs/" + jobId);
        zkData.setGmtCreate(new Date());

        byte[] bytes = ObjectByteConvert.obj2Byte(ObjectConverter.obj2Json(zkData));
        zkData.setData(bytes);
        zkClientService.create(submittedPath, bytes);

        Object data = zkClientService.getData(submittedPath);
        ZkData json2Obj = ObjectConverter.json2Obj((String) data, ZkData.class);
        System.out.println(ObjectConverter.obj2Json(json2Obj));
    }

}
