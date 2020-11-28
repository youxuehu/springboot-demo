package org.example;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.example.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @SofaReference
    TestService testService;

    @RequestMapping("/execute")
    @ResponseBody
    public String execute() {
        String message = testService.message("How are you?");
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
        return "execute success";
    }
}
