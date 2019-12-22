package com.example.springbootdemo.common.hadoop1;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class HadoopService implements InitializingBean {

    @Autowired
    private HadoopUtil hadoopUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        hadoopUtil.mkdir("/test3");
        hadoopUtil.delete("/test2");
        hadoopUtil.delete("/test3");
        System.out.println(hadoopUtil.fileStatus("/1.data"));
        System.out.println(hadoopUtil.createFile("/1.txt"));
        hadoopUtil.uploadFile("/Users/youxuehu/IdeaProjects/" +
                "springboot-demo/src/main/java/com/example/springbootdemo/" +
                "common/hadoop1/HadoopService.java", "/HadoopService.java");
        System.out.println(hadoopUtil.readFile("/1.data"));
        hadoopUtil.downloadFile("/1.data", "/Users/youxuehu/八斗学院/SVIP 预习课程/2 生态架构课/1.data");
    }
}
