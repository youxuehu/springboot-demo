package com.example.springbootdemo.common.redis;

import com.example.springbootdemo.common.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RedisComonent implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(RedisComonent.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired @Qualifier("redisCacheServiceImpl")
    CacheService cacheService;

    @Override
    public void afterPropertiesSet() throws Exception {
        // string
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name_redis", "recommend system");
        String name = (String) valueOperations.get("name_redis");
        LOG.info("测试redis的String数据类型，查询name=>{}", name);
        // list
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", Arrays.asList("a", "b"));
        List<String> list = (List<String>) listOperations.leftPop("list");
        LOG.info("测试redis的List数据类型{leftPush， leftPop}，查询list=>{}", list);
        // hash
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "zhang3");
        hashMap.put("age", 28);
        hashOperations.put("hash", "1", hashMap);
        Object hash = hashOperations.get("hash", "1");
        LOG.info("测试redis的Hash数据类型{put， get}，查询hash=>{}", hash);
        // set
        SetOperations setOperations = redisTemplate.opsForSet();
        Set<Object> setObj = new HashSet<>();
        setObj.add("jack");
        setObj.add("jack");
        setObj.add("tom");
        setOperations.add("set_Hash", setObj);
        // members： 只是单纯的获取
        Set setMembers = setOperations.members("set_Hash");
        LOG.info("测试redis的Set数据类型{add， members}，查询setMembers=>{}", setMembers);
        Object randomMember = setOperations.randomMember("set_Hash");
        LOG.info("测试redis的Set数据类型{add， randomMember}，查询randomMember=>{}", randomMember);
        // pop ： 获取玩数据，就删除
        Object setPop = setOperations.pop("set_Hash");
        LOG.info("测试redis的Set数据类型{add， pop}，查询setPop=>{}", setPop);
        // 再次获取就是null
        Object setPop2 = setOperations.pop("set_Hash");
        LOG.info("测试redis的Set数据类型{add， pop}，再次查询setPop2=>{}", setPop2);
        // zset
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("name", "yxh", 1);
        zSetOperations.add("name", "yxh", 1);
        zSetOperations.add("name", "yxh", 3);
        Set zSetName = zSetOperations.range("name", 1, 3);
        LOG.info("测试redis的ZSet数据类型{add， range}，查询zSetName=>{}", zSetName);
        // keys *
        Set keys = redisTemplate.keys("keys *");
        LOG.info("测试redis的keys *，查询keys=>{}", keys);
        Set<String> like = cacheService.like("key1234*");
        LOG.info("测试redis的keys *，查询keys=>{}", like);
    }
}
