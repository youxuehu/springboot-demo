package com.h2t.study.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoCopntroller {

    private Logger LOGGER = LoggerFactory.getLogger(DemoCopntroller.class);

    @RequestMapping("/aa")
    public Object aa(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        Map<String, String> data = new HashMap<>();
        data.put("name", "jack");
        data.put("requestUrl", requestUrl);
        LOGGER.info(JSON.toJSONString(data));
        return data;
    }
}
