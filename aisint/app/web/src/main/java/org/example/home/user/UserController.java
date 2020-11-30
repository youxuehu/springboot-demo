package org.example.home.user;

import com.alibaba.fastjson.JSON;
import org.example.db.user.model.UserInfo;
import org.example.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("insert")
    @ResponseBody
    public Integer insert(HttpServletRequest request) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail("youxuehu@hotmail.com");
            userInfo.setUsername("youxuehu" + atomicInteger.getAndIncrement());
            userInfo.setTel("16619794106");
            userInfo.setQq("1184483621");
            userInfo.setRealname("游学虎" + atomicInteger.getAndIncrement());
            userInfo.setUsertype("1");
            userInfo.setEnabled(0);
            userInfo.setPassword(System.currentTimeMillis() + "");
            if (logger.isInfoEnabled()) {
                logger.info(JSON.toJSONString(userInfo, true));
            }
            userService.insert(userInfo);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("queryList")
    @ResponseBody
    public List<UserInfo> queryList(HttpServletRequest request) {
        List<UserInfo> userInfos = userService.queryList();
        if (logger.isInfoEnabled()) {
            logger.info(JSON.toJSONString(userInfos, true));
        }
        return userInfos;
    }
}
