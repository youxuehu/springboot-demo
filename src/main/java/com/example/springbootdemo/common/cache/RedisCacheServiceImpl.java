package com.example.springbootdemo.common.cache;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RedisCacheServiceImpl implements CacheService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void set(String key, Object value, long expire) {
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("key is null");
        }
        if (value == null) {
            throw new RuntimeException("value is null");
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("开始写入Redis缓存>>>>>>>>>>>>>>>key:", key);
        }
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("写入Redis缓存>>>>>>>>>>>>>>>结束");
        }
    }

    @Override
    public void set(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("key is null");
        }
        if (value == null) {
            throw new RuntimeException("value is null");
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("开始写入Redis缓存>>>>>>>>>>>>>>>key:", key);
        }
        redisTemplate.opsForValue().set(key, value);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("写入Redis缓存>>>>>>>>>>>>>>>结束");
        }
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("key is null");
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, long timeout, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("key is null");
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("开始删除Redis缓存>>>>>>>>>>>>>>>key:", key);
        }
        redisTemplate.delete(key);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("删除Redis缓存>>>>>>>>>>>>>>>结束");
        }
    }

    /**
     * 有3个通配符 *, ? ,[]
     *
     * *: 通配任意多个字符
     *
     * ?: 通配单个字符
     *
     * []: 通配括号内的某1个字符
     * keys *
     * keys o*
     * keys *o
     * keys ???
     * keys on?
     * keys on[eaw]
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<String> like(String pattern) {
        List<String> listKeys = (List<String>) redisTemplate.execute(connection -> {
            //scan 迭代遍历键，返回的结果可能会有重复，需要客户端去重复
            Set<String> redisKeys = new HashSet<>();
            //lettuce 原生api
            RedisAsyncCommands conn = (RedisAsyncCommands) connection.getNativeConnection();
            //游标
            ScanCursor curs = ScanCursor.INITIAL;
            try {
                //采用 SCAN 命令，迭代遍历所有key
                while (!curs.isFinished()) {
                    long count = 10000L;
                    ScanArgs args = ScanArgs.Builder.matches(pattern).limit(count);
                    LOGGER.info("SCAN {} MATCH {} COUNT {}", curs.getCursor(), pattern, count);
                    RedisFuture<KeyScanCursor<byte[]>> future = conn.scan(curs, args);
                    KeyScanCursor<byte[]> keyCurs = future.get();
                    List<byte[]> ks = keyCurs.getKeys();
                    Set<String> set = ks.stream().map(bytes -> new String(bytes, StandardCharsets.UTF_8)).collect(Collectors.toSet());
                    LOGGER.info("return size:{}", set.size());
                    redisKeys.addAll(set);
                    curs = keyCurs;
                }
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("redis模糊查询异常,pattern:" + pattern, e);
                throw new RuntimeException(e);
            }
            return new ArrayList<>(redisKeys);
        }, true);
        return listKeys.stream().collect(Collectors.toSet());
    }
}
