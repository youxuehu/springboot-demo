package com.example.springbootdemo.common.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisCache implements Cache {

    private String id;

    public MybatisCache(String id) {
        this.id = id;
    }

    @Autowired
    CacheService cacheService;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cacheService.set((String) key, value);
    }

    @Override
    public Object getObject(Object key) {
        return cacheService.get((String) key, Object.class);
    }

    @Override
    public Object removeObject(Object key) {
        Object result = cacheService.get((String) key, Object.class);
        cacheService.delete((String) key);
        return result;
    }

    @Override
    public void clear() {
        Set<String> keys = cacheService.like(id);
        for (String key : keys) {
            cacheService.delete(key);
        }
    }

    @Override
    public int getSize() {
        return 0;
    }
}
