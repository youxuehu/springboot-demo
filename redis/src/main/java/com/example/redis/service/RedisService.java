package com.example.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisService {
	Logger logger = LoggerFactory.getLogger(RedisService.class);
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public RedisService(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    
    public List<Object> add(){
    	logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for (int  i = 0; i < 20000; i++) {
        	redisTemplate.opsForValue().set("key" + i, "value" + i);
        }
        Set<String> keys = redisTemplate.keys("key*");
        List<Object> multiGet = redisTemplate.opsForValue().multiGet(keys);
        if (logger.isInfoEnabled()) {
        	ObjectMapper objectMapper = new ObjectMapper();
        	String mjson = null;
			try {
				mjson = objectMapper.writeValueAsString(multiGet);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
        	logger.info(mjson);
        }
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return multiGet;
    }

    public String stringTest(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) redisTemplate.opsForValue().get("kak");
    }

    public Map<Object,Object> hashTest(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取整个map
        return redisTemplate.opsForHash().entries("user");
    }

    public Set<Object> setTest(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取所有元素
        return  redisTemplate.opsForSet().members("你好");
    }

    public List<Object> listTest(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  redisTemplate.opsForList().range("left-list",0,100);
    }
}