package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected ModelMap success(Object... param) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        if (param.length == 0) {
            LOGGER.info(JSON.toJSONString(modelMap, true));
            return modelMap;
        }
        if (param.length % 2 == 1) {
            throw new RuntimeException("param must be even");
        }
        Object key = null;
        Object value = null;
        Map data = new HashMap();
        for (int i = 0; i < param.length; i++) {
            if (i % 2 == 0) {
                key = param[i];
            } else {
                value = param[i];
                data.put(key, value);
            }
        }
        modelMap.put("data", data);
        LOGGER.info(JSON.toJSONString(modelMap, true));
        return modelMap;
    }

    protected ModelMap error(Object... param) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", false);
        if (param.length == 0) {
            LOGGER.error(JSON.toJSONString(modelMap, true));
            return modelMap;
        }
        if (param.length % 2 == 1) {
            throw new RuntimeException("param must be even");
        }
        Object key = null;
        Object value = null;
        Map data = new HashMap();
        for (int i = 0; i < param.length; i++) {
            if (i % 2 == 0) {
                key = param[i];
            } else {
                value = param[i];
                modelMap.put((String) key, value);
            }
        }
        LOGGER.error(JSON.toJSONString(modelMap, true));
        return modelMap;
    }
}
