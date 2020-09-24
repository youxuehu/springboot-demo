package org.example.lock.memcached.impl;

import org.example.lock.memcached.MemcachedService;

public class MemcachedLock {

    private static final String MEMCACHED_LOCK_KEY = "MEMCACHED_LOCK";
    private static final String MEMCACHED_LOCK_VALUE = "MEMCACHED_LOCK_VALUE";

    private MemcachedService memcachedService;

    public boolean tryLock() {
        return memcachedService.add(MEMCACHED_LOCK_KEY, MEMCACHED_LOCK_VALUE, 3600);
    }

    public boolean releaseLock() {
        return memcachedService.delete(MEMCACHED_LOCK_KEY);
    }

    public void setMemcachedService(MemcachedService memcachedService) {
        this.memcachedService = memcachedService;
    }
}
