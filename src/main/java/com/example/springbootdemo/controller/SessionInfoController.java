package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.controller.userinfos.param.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInfoController.class);

    @RequestMapping("/session")
    @ResponseBody
    public SessionInfo session(SessionInfo sessionInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("sessionInfo=>" + JSON.toJSONString(sessionInfo));
        }
        return sessionInfo;
    }
}
