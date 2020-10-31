package com.example.springbootdemo.common.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocalCache {
    private static final Map<String, CachedObj> CACHED = new ConcurrentHashMap<>();
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) {
        LocalCache localCache = new LocalCache();
        localCache.set("name", "jack", 7 * 1000L);
        try {
            Thread.sleep(6 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = localCache.get("name", String.class);
        System.out.println("name=" + name);
    }

    public void set(String key, Object value, long expire) {
        Long currentTime = null;
        if (expire <= 0) {
            if (expire == -1) {
                currentTime = -1L;
            } else {
                return;
            }
        }
        if (currentTime == null) {
            currentTime = System.currentTimeMillis() + expire;
        }

        CachedObj cachedObj = new CachedObj(value, currentTime);
        CACHED.put(key, cachedObj);
    }

    public void set(String key, Object value) {
        set(key, value, -1);
    }

    public <T> T get(String key, Class<T> clazz) {
        startCleanCached();
        if (checkCache(key)) {
            CachedObj cachedObj = CACHED.get(key);
            return (T)cachedObj.getValueObj();
        }
        return null;
    }

    private void startCleanCached() {
        if (!atomicBoolean.getAndSet(true)) {
            CleanCacheTask cleanCacheTask = new CleanCacheTask();
            Thread thread = new Thread(cleanCacheTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public <T> T get(String key, long timeout, Class<T> clazz) {
        if (timeout > 0) {
            return get(key, clazz);
        }
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "CachedPool");
            }
        });
        try {
            Callable<T> getTask = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return get(key, clazz);
                }
            };
            Future<T> future = pool.submit(getTask);
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        return null;
    }

    private boolean checkCache(String key) {
        CachedObj cachedObj = CACHED.get(key);
        if (cachedObj == null) {
            return false;
        }
        if (cachedObj.getValueTime() < System.currentTimeMillis() && cachedObj.getValueTime() != -1) {
            delete(key);
            return false;
        }
        return true;
    }

    private void delete(String key) {
        CachedObj cachedObj = CACHED.get(key);
        if (cachedObj != null) {
            CACHED.remove(key);
        }
    }

    private class CachedObj {
        private Object valueObj;
        private Long valueTime;

        public CachedObj() {
        }

        public CachedObj(Object valueObj, Long valueTime) {
            this.valueObj = valueObj;
            this.valueTime = valueTime;
        }

        public Object getValueObj() {
            return valueObj;
        }

        public void setValueObj(Object valueObj) {
            this.valueObj = valueObj;
        }

        public Long getValueTime() {
            return valueTime;
        }

        public void setValueTime(Long valueTime) {
            this.valueTime = valueTime;
        }
    }

    private class CleanCacheTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                List<String> keys = new LinkedList<>();
                for (Map.Entry<String, CachedObj> entry : CACHED.entrySet()) {
                    String key = entry.getKey();
                    CachedObj cachedObj = entry.getValue();
                    if (cachedObj.getValueTime() < System.currentTimeMillis() && cachedObj.getValueTime() != -1) {
                        keys.add(key);
                    }
                }
                for (String key : keys) {
                    delete(key);
                }
                try {
                    Thread.sleep(60 * 1000L);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
