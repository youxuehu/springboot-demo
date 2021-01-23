package com.example.springbootdemo.controller.execution;

import com.example.springbootdemo.common.db.dao.executionlog.model.ExecutionLog;
import com.example.springbootdemo.common.db.service.ExecutionLogService;
import com.example.springbootdemo.controller.BaseController;
import com.example.springbootdemo.controller.execution.param.ParamVo;
import com.example.springbootdemo.manager.JobManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
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
    @RequestMapping("/execute")
    @ResponseBody
    public ModelMap execute(@RequestBody ParamVo paramVo) {
        String cmd = paramVo.getCmd();
        if (StringUtils.isBlank(cmd)) {
            return error("errorMessage", "cmd为空");
        }
        String jobId = jobManager.submit(cmd);
        return success("jobId", jobId);
    }

    @RequestMapping("/queryLog")
    @ResponseBody
    public ModelMap queryLog(@RequestParam String jobId) {
        List<ExecutionLog> logs = executionLogService.queryLogsByJobIds(jobId);
        return success("logs", logs);
    }
}
