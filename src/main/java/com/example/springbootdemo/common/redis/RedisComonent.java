package com.example.springbootdemo.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//@Component
public class RedisComonent implements InitializingBean {

    Logger LOG = LoggerFactory.getLogger(RedisComonent.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.opsForValue().set("name_redis", "recommend system");
        String name = (String) redisTemplate.opsForValue().get("name_redis");
        LOG.info("name=>{}", name);
    }
}
