package com.example.springbootdemo.handler.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.ShellUtil;
import com.example.springbootdemo.handler.AbstractJobHandler;
import com.example.springbootdemo.handler.JobHandler;
import com.example.springbootdemo.handler.TaskTypeEnum;
import com.example.springbootdemo.manager.ExecutionContext;
import com.example.springbootdemo.manager.res.ResultLog;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SingletonJobHandler extends AbstractJobHandler implements JobHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonJobHandler.class);

    @Override
    public void invoke(ExecutionContext executionContext, String content) {
        // 替换特殊字符掩码
        content = content.replaceAll("user_access_key=\\w+", "user_access_key=******");
        executionContext.getMsgSender().send(new ResultLog(new Date(), new Date() + "  start SingletonJobHandler", executionContext.getJobId()));
        executionContext.getMsgSender().send(new ResultLog(new Date(), new Date() + " exec cmd :" + content, executionContext.getJobId()));
        try {
            ArrayList<String> cmds = Lists.newArrayList();
            cmds.add("/bin/sh");
            cmds.add("-c");
            cmds.add(content);
            executionContext.getMsgSender().send(new ResultLog(new Date(), new Date() + " exec cmd :" + JSON.toJSONString(cmds), executionContext.getJobId()));
            List<String> execLogs = ShellUtil.runShell(cmds);
            execLogs.forEach(str -> {
                executionContext.getMsgSender().send(new ResultLog(new Date(), str, executionContext.getJobId()));
            });
            executionContext.getMsgSender().send(new ResultLog(new Date(), new Date() + "  end SingletonJobHandler", executionContext.getJobId()));
        } catch (Exception e) {
            LOGGER.error("SingletonJobHandler invoke error", e);
        }
    }

    @Override
    public TaskTypeEnum getType() {
        return TaskTypeEnum.SINGLETON;
    }

}
