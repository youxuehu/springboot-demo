package org.example.lock.memcached.impl;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import org.example.lock.memcached.MemcachedService;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

public class MemcachedServiceImpl implements MemcachedService, InitializingBean {

    private String host;
    private int port;
    private MemcachedClient memcachedClient;

    @Override
    public boolean add(String key, String value, int expire) {
        OperationFuture<Boolean> operationFuture = memcachedClient.add(key, expire, value);
        try {
            return operationFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String get(String key) {
        return (String) memcachedClient.get(key);
    }

    @Override
    public boolean delete(String key) {
        OperationFuture<Boolean> operationFuture = memcachedClient.delete(key);
        try {
            return operationFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        memcachedClient = new MemcachedClient(new InetSocketAddress(host, port));
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
