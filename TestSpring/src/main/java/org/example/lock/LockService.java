package org.example.lock;


public interface LockService {
    long tryLock(String key);
    long lock(String key) throws InterruptedException;
    void unlock(String key,long expireTime);
}
