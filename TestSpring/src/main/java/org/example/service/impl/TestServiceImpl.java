package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.MyApplicationContextAware;
import org.example.service.TestService;

public class TestServiceImpl implements TestService {
    private MyApplicationContextAware myApplicationContextAware;
    @Override
    public String test() {
        return JSON.toJSONString(myApplicationContextAware.getApplicationContext().getBeanDefinitionNames(), true);
    }

    public void setMyApplicationContextAware(MyApplicationContextAware myApplicationContextAware) {
        this.myApplicationContextAware = myApplicationContextAware;
    }
}
