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
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
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

    @RequestMapping("/print")
    @ResponseBody
    public void print(HttpServletRequest request) {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(Thread.currentThread().getName());
            logger.info(request.getRequestURL().toString());
        }
    }
}
