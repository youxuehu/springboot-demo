package com.example.springbootdemo.controller.hadoop;

import com.example.springbootdemo.common.db.dao.executionlog.model.ExecutionLog;
import com.example.springbootdemo.common.db.service.ExecutionLogService;
import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import com.example.springbootdemo.controller.BaseController;
import com.example.springbootdemo.controller.hadoop.param.TaskVO;
import com.example.springbootdemo.manager.JobManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.springbootdemo.utils.ShellUtil.runShell;

@Controller
@RequestMapping("/execute")
public class ExecuteAjaxController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteAjaxController.class);

    @Autowired
    JobManager jobManager;

    @Autowired
    ExecutionLogService executionLogService;

    /**
     * 提交任务到yarn
     *
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ModelMap execute(@RequestBody String json) {
        String jobId = jobManager.submit(json);
        return success("jobId", jobId);
    }

    @RequestMapping("queryLog")
    @ResponseBody
    public ModelMap queryLog(@RequestParam String jobId) {
        List<ExecutionLog> logs = executionLogService.queryLogsByJobIds(jobId);
        return success("logs", logs);
    }
}
