package org.example.service.demotask;

import com.alibaba.fastjson.JSON;
import org.example.db.dao.demotask.model.DemoTask;
import org.example.db.service.demotask.DemoTaskService;
import org.example.db.service.demotask.impl.DemoTaskServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DemoTaskTest {

    @Test
    public void insert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoTaskService demoTaskService = applicationContext.getBean(DemoTaskServiceImpl.class);
        DemoTask demoTask = new DemoTask();
        demoTask.setTaskName("my-task01");
        demoTaskService.insert(demoTask);
    }


    @Test
    public void queryAll() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoTaskService demoTaskService = applicationContext.getBean(DemoTaskServiceImpl.class);
        List<DemoTask> list = demoTaskService.queryAll();
        System.out.println(JSON.toJSONString(list, true));
    }

}
