package com.example.springbootdemo.utils;

import java.util.UUID;

/**
 * @author youxuehu
 * @version v1.0
 * @className UUID
 * @date 2021/5/10 10:42 下午
 * @desrription 这是类的描述信息
 */
public class UUIDUtil {

    public static void main(String[] args) {
        System.out.println(getUniqueId());
    }
    
    public static Long getUniqueId() {
        return Long.valueOf(UUID.randomUUID().hashCode() & Integer.MAX_VALUE);
    }
}
