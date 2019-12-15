package com.example.springbootdemo.common.hadoop1_2_1;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HadoopService implements InitializingBean {

    @Autowired
    private HadoopUtil hadoopUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        hadoopUtil.mkdir("/test3");
        hadoopUtil.delete("/test2");
        hadoopUtil.delete("/test3");
    }
}
