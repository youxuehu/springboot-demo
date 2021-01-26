package com.example.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ObjectConverter {

    public static String obj2Json(Object o) {
        return JSON.toJSONString(o, SerializerFeature.WriteClassName);
    }

    public static <T> T json2Obj(String json, Class<T> tClass) {
        return JSON.parseObject(json, tClass);
    }
}
