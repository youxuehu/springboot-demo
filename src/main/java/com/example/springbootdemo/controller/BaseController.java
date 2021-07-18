package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.biz.RequestContextService;
import com.example.common.model.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    RequestContextService requestContextService;

    protected RequestContext initRequestContext(HttpServletRequest request) {
        return requestContextService.initRequestContext(request);
    }

    protected ModelMap success(HttpServletRequest request, Object... param) {
        String requestUrl = request.getRequestURL().toString();
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        if (param.length == 0) {
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
        LOGGER.info(requestUrl + " ------> " + JSON.toJSONString(modelMap, true));
        return modelMap;
    }

    protected ModelMap error(HttpServletRequest request, Object... param) {
        String requestUrl = request.getRequestURL().toString();
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
        LOGGER.info(requestUrl + " ------> " + JSON.toJSONString(modelMap, true));
        return modelMap;
    }
}
