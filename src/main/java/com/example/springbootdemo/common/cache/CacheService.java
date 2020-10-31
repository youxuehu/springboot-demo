package com.example.springbootdemo.common.cache;

public interface CacheService {
    void set(String key, Object value, long expire);
    void set(String key, Object value);
    <T> T get(String key, Class<T> clazz);
    <T> T get(String key, long timeout, Class<T> clazz);
    void delete(String key);
}
