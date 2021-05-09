package com.example.springbootdemo.common.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import org.spark_project.jetty.util.BlockingArrayQueue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author youxuehu
 * @version v1.0
 * @className LoadingCache
 * @date 2021/5/8 10:23 下午
 * @desrription 这是类的描述信息
 */
public class LoadingCacheBean {

//    private static final LoadingCache<String, Map<String, SlsConfig>> slsConfigCache =
//            CacheBuilder.newBuilder()
//                    .build(new CacheLoader<String, Map<String, SlsConfig>>() {
//        @Override
//        public Map<String, SlsConfig> load(String key) throws Exception {
//            return null;
//        }
//    });
//
//    static {
//        Map<String, SlsConfig> data = new HashMap<>();
//        slsConfigCache.put("dmsint", data);
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final LoadingCache<String, String> caches = CacheBuilder.newBuilder()
                .maximumSize(2) // 缓存数量上限
                .expireAfterAccess(1, TimeUnit.SECONDS) // 缓存在指定时间内没有被读写则回收
                .expireAfterWrite(1, TimeUnit.SECONDS) // 缓存在指定时间内没有被写操作则回收
                .removalListener(new RemovalListener<String, String>() {
                    /**
                     * 此方法时同步的
                     * @param notification
                     */
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        String key = notification.getKey();
                        String value = notification.getValue();
                        // 当缓存被移除时触发

                    }
                })
                .removalListener(RemovalListeners.asynchronous(new RemovalListener<String, String>() {

                    /**
                     * 异步执行
                     * @param notification
                     */
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {

                    }
                },  new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new BlockingArrayQueue<>(1))))
                .build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                // 初始化数据，如果没有则默认规则
                return "zhansan";
            }
        });

        // 写缓存
        caches.put("a", "a");
        caches.put("b", "b");
        caches.put("c", "c");
        caches.put("d", "d");

        // 移除缓存a
        caches.invalidate("a");
        // 批量移除
        caches.invalidateAll(Arrays.asList("a", "b"));
        // 移除所有
        caches.invalidateAll();


        System.out.println(caches.size());

        ConcurrentMap<String, String> stringStringConcurrentMap = caches.asMap();
        System.out.println(stringStringConcurrentMap);

        System.out.println(caches.get("c"));
        Thread.sleep(1000);
        System.out.println(caches.get("c"));
    }



}
