package org.example.db.service.demotask;

import org.example.db.dao.demotask.model.DemoTask;

import java.util.List;

public interface DemoTaskService {

    void insert(DemoTask demoTask);

    List<DemoTask> queryAll();
}
