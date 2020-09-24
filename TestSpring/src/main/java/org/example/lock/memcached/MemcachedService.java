package org.example.lock.memcached;

public interface MemcachedService {

    boolean add(String key, String value, int expire);

    String get(String key);

    boolean delete(String key);
}
