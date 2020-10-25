package com.example.springbootdemo.controller.hadoop;
import com.example.springbootdemo.controller.hadoop.param.TaskVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.springbootdemo.utils.ShellUtil.runShell;

@Controller
@RequestMapping("/yarn")
public class YarnJobController {
    @Value("${hadoop_client}")
    String hadoopClient;
    @Value("${yarn_client}")
    String yarnClient;
    /**
     * 提交任务到yarn
     * @return
     */
    @RequestMapping("submit")
    @ResponseBody
    public Object submit(@RequestBody TaskVO taskVO) {
        Map<String, String> result = new HashMap<>();
        List<String> cmds = new ArrayList<>();
        cmds.add("/bin/sh");
        cmds.add("-c");
        StringBuilder stringBuilder = new StringBuilder(" jar ");
        stringBuilder.append(taskVO.getJarPath() + " ");
        stringBuilder.append(taskVO.getClassName() + " ");
        stringBuilder.append(taskVO.getInputPath() + " ");
        stringBuilder.append(taskVO.getOutputPath());
        cmds.add(hadoopClient+ stringBuilder.toString());
        try {
            List<String> logs = runShell(cmds);
            for (String log : logs) {
                if (log.contains("application_")) {
                    result.put("appId", log.substring(log.indexOf("application_")));
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提交任务到yarn
     * @return
     */
    @RequestMapping("queryStatus")
    @ResponseBody
    public Object queryStatus(String appId) {
        Map<String, String> result = new HashMap<>();
        List<String> cmds = new ArrayList<>();
        cmds.add("/bin/sh");
        cmds.add("-c");
        cmds.add(yarnClient+ " application -status " + appId);
        try {
            List<String> logs = runShell(cmds);
            for (String log : logs) {
                if (log.contains("Final-State")) {
                    result.put("execStatus", log.split(":")[1].trim());
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
