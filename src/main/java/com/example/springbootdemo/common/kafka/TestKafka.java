package com.example.springbootdemo.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class TestKafka implements InitializingBean {
//    @Autowired
    KafkaSender sender;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i <1000; i++) {
            sender.send();
            log.info("send message ok!!!!");
        }
    }
}
