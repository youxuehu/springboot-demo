package com.example.springbootdemo.common.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    public static void main(String[] args) throws ExecutionException {
        final LoadingCache<String, String> caches = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                // 初始化数据，如果没有则默认
                return "zhansan";
            }
        });

        caches.put("name", "lisi");
        caches.put("age", "20");
        caches.put("email", "lisi@qq.com");
        caches.put("sex", "male");

        System.out.println(caches.size());

        String name = caches.get("xxx");
        System.out.println(name);
    }



}
