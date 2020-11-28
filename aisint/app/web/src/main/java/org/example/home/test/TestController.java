package org.example.home.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.example.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @SofaReference
    TestService testService;

    @RequestMapping("message")
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
