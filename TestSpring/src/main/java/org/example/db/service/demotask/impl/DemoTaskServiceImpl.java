package org.example.db.service.demotask.impl;

import org.example.db.dao.demotask.mapper.DemoTaskMapper;
import org.example.db.dao.demotask.model.DemoTask;
import org.example.db.dao.demotask.model.DemoTaskExample;
import org.example.db.service.demotask.DemoTaskService;

import java.util.List;

public class DemoTaskServiceImpl implements DemoTaskService {

    private DemoTaskMapper demoTaskMapper;

    @Override
    public void insert(DemoTask demoTask) {
        demoTaskMapper.insert(demoTask);
    }

    @Override
    public List<DemoTask> queryAll() {
        return demoTaskMapper.selectByExample(new DemoTaskExample());
    }

    public void setDemoTaskMapper(DemoTaskMapper demoTaskMapper) {
        this.demoTaskMapper = demoTaskMapper;
    }
}
