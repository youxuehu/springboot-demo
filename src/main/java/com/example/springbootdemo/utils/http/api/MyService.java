package com.example.springbootdemo.utils.http.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);

    @Autowired
    private MyClient myClient;

    public String callBaidu() {
        LOGGER.warn("call http api forest interface");
        String result = myClient.baidu();
        LOGGER.warn(result);
        return result;
    }

    public String callTaoBao() {
        LOGGER.warn("call http api forest interface");
        String result = myClient.taobao();
        LOGGER.warn(result);
        return result;
    }

}