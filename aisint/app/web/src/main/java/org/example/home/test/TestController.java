package org.example.home.test;

import org.example.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService testService;

    @RequestMapping("message")
    @ResponseBody
    public String message(HttpServletRequest request, String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
        String result = testService.message(message);
        if (logger.isInfoEnabled()) {
            logger.info(result);
        }
        return result;
    }
}
