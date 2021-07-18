package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.holder.SessionInfo;
import com.example.springbootdemo.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionInfoController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInfoController.class);

    @RequestMapping("/session")
    @ResponseBody
    public ModelMap session(SessionInfo sessionInfo, TestVO testVO) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("sessionInfo=>" + JSON.toJSONString(sessionInfo));
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("testVO=>" + JSON.toJSONString(testVO));
        }
        return success("sessionInfo", sessionInfo, "testVO", testVO);
    }
}
