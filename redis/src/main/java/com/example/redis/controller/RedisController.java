package com.example.redis.controller;

import com.example.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RedisController {
    private RedisService redisService;
    @Autowired
    public RedisController(RedisService redisService){
        this.redisService = redisService;
    }
    
    @GetMapping("add")
    public List<Object> add(){
        return redisService.add();
    }

    @GetMapping("string")
    public String stringTest(){
        return redisService.stringTest();
    }

    @GetMapping("hash")
    public Map<Object, Object> hashTest(){
        return redisService.hashTest();
    }

    @GetMapping("set")
    public Set<Object> setTest(){
        return redisService.setTest();
    }

    @GetMapping("list")
    public List<Object> listTest(){
        return redisService.listTest();
    }
}