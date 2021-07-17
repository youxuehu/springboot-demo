package com.example.springbootdemo.controller.hadoop;

import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import com.example.springbootdemo.controller.BaseController;
import com.example.springbootdemo.controller.hadoop.param.TaskVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.common.utils.ShellUtil.runShell;

@Controller
@RequestMapping("/yarn")
public class YarnJobController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(YarnJobController.class);

    @Value("${hadoop_client}")
    String hadoopClient;

    @Value("${yarn_client}")
    String yarnClient;

    @Autowired
    HDFS2Utils hdfs2Utils;

    @RequestMapping("manager")
    public String manager() {
        return "HadoopManager";
    }

    /**
     * 提交任务到yarn
     *
     * @return
     */
    @RequestMapping("submit")
    @ResponseBody
    public ModelMap submit(TaskVO taskVO) {
        List<String> cmds = new ArrayList<>();
        MultipartFile jarPath = taskVO.getJarPath();
        if (jarPath.isEmpty()) {
            throw new RuntimeException("jarPath is null");
        }
        String jarFileName = jarPath.getOriginalFilename();
        String jarFilePath = "/tmp/" + jarFileName;
        try {
            if (!hdfs2Utils.isExistsFile(taskVO.getInputPath())) {
                return error("errorMessage", "input path not exists!");
            }
            hdfs2Utils.delete(taskVO.getOutputPath());
            InputStream inputStream = jarPath.getInputStream();
            FileUtils.writeByteArrayToFile(new File(jarFilePath), IOUtils.toByteArray(inputStream));

            cmds.add("/bin/sh");
            cmds.add("-c");
            StringBuilder stringBuilder = new StringBuilder(" jar ");
            stringBuilder.append(jarFilePath + " ");
            stringBuilder.append(taskVO.getClassName() + " ");
            stringBuilder.append(taskVO.getInputPath() + " ");
            stringBuilder.append(taskVO.getOutputPath());
            cmds.add(hadoopClient + stringBuilder.toString());
            String appId = null;

            List<String> logs = runShell(cmds);
            for (String log : logs) {
                if (log.contains("Submitted application ")) {
                    appId = log.split("Submitted application ")[1];
                    break;
                }
            }
            if (StringUtils.isBlank(appId)) {
                return error("errorMessage", logs);
            }
            return success("appId", appId);
        } catch (Exception e) {
            return error("errorMessage", e.toString());
        } finally {
            try {
                FileUtils.forceDeleteOnExit(new File(jarFilePath));
            } catch (IOException e) {

            }
        }
    }

    /**
     * 提交任务到yarn
     *
     * @return
     */
    @RequestMapping("queryStatus")
    @ResponseBody
    public ModelMap queryStatus(String appId) {
        List<String> cmds = new ArrayList<>();
        cmds.add("/bin/sh");
        cmds.add("-c");
        cmds.add(yarnClient + " application -status " + appId);
        String status = null;
        try {
            List<String> logs = runShell(cmds);
            for (String log : logs) {
                if (log.contains("Final-State")) {
                    status = log.split(":")[1].trim();
                    break;
                }
            }
            return success("status", status);
        } catch (Exception e) {
            return error("errorMessage", e.toString());
        }
    }

    @RequestMapping("queryLog")
    @ResponseBody
    public ModelMap queryLog(@RequestParam String appId) {
        List<String> cmds = new ArrayList<>();
        cmds.add("/bin/sh");
        cmds.add("-c");
        cmds.add(yarnClient + " logs -applicationId " + appId);
        try {
            List<String> logs = runShell(cmds);
            return success("logs", logs);
        } catch (Exception e) {
            return error("errorMessage", e.toString());
        }
    }
}
