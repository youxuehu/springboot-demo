package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    @Qualifier("demo")
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void addStringTest(){
        //添加并设置过期时间
        redisTemplate.opsForValue().set("kak",4552,60, TimeUnit.SECONDS);
        //自增
        redisTemplate.opsForValue().increment("kak",1);
        //获取重新设置value
        redisTemplate.opsForValue().getAndSet("kak","hello");
        //追加到字符串的末尾
        redisTemplate.opsForValue().append("kak","world");
        //获取字符长度
        redisTemplate.opsForValue().size("kak");
    }

    @Test
    void addHashTest(){
        //添加单个
        redisTemplate.opsForHash().put("user","id","001");
        //点加多个
        Map<String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("sex","男");
        map.put("age","20");
        redisTemplate.opsForHash().putAll("user",map);
        //键集合
        Set<Object> keys =  redisTemplate.opsForHash().keys("user");
        System.out.println("keys:"+keys);
        //value集合
        List<Object> values =  redisTemplate.opsForHash().values("user");
        System.out.println("values:"+values);
        //遍历map
        Cursor<Map.Entry<Object,Object>> entryCursor  = redisTemplate.opsForHash().scan("user", ScanOptions.NONE);
        while (entryCursor.hasNext()){
            Map.Entry<Object,Object> entry =entryCursor.next();
            System.out.println("键："+entry.getKey()+"  值："+entry.getValue());
        }
    }

    @Test
    void addSetTest(){
        String lkl = "你好";
        //添加一个或者多个
        String[] ste = new String[]{"123","456","789","45","6"};
        redisTemplate.opsForSet().add(lkl,ste);
        //移除一个或多个
        ste= new String[]{"123"};
        redisTemplate.opsForSet().remove(lkl, (Object) ste);
        //遍历
        Cursor<Object> cursor = redisTemplate.opsForSet().scan(lkl,ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println("set成员元素："+cursor.next());
        }
    }

    @Test
    void addListTest(){
        //表头插入单个
        redisTemplate.opsForList().leftPush("left-list","java");
        //表头插入多个
        String [] arr = new String[]{"js","html","c#","C++"};
        redisTemplate.opsForList().leftPushAll("left-list",arr);
        //表尾插入单个
        redisTemplate.opsForList().rightPush("rught-list","java");
        //表尾插入多个
        redisTemplate.opsForList().rightPushAll("rught-list",arr);
        //设置位置
        redisTemplate.opsForList().set("rught-list",0,"第一个");
        //删除:count> 0：删除等于从头到尾移动的值的元素。count <0：删除等于从尾到头移动的值的元素。count = 0：删除等于value的所有元素。
        redisTemplate.opsForList().remove("rught-list",1,"js");//
    }

}