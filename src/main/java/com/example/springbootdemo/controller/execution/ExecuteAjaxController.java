package com.example.springbootdemo.controller.execution;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.common.db.dao.executionlog.model.ExecutionLog;
import com.example.common.db.dao.zkdata.model.ZkData;
import com.example.common.db.service.executionlog.ExecutionLogService;
import com.example.common.db.service.zk.ZkClientService;
import com.example.common.model.RequestContext;
import com.example.common.utils.UUIDUtil;
import com.example.springbootdemo.controller.BaseController;
import com.example.springbootdemo.controller.execution.param.ParamVo;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.example.dubbo.api.job.JobControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ExecuteAjaxController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteAjaxController.class);

    @Reference
    JobControlService jobControlService;

    @Autowired
    ExecutionLogService executionLogService;

    @Autowired
    ZkClientService zkClientService;

    /**
     * 提交任务到yarn
     *
     * @return
     */
    @RequestMapping("/execute")
    @ResponseBody
    public ModelMap execute(HttpServletRequest request, @RequestBody ParamVo paramVo) {
        String cmd = paramVo.getCmd();
        if (StringUtils.isBlank(cmd)) {
            return error("errorMessage", "cmd为空");
        }
        RequestContext requestContext = initRequestContext(request);
        String jobId = jobControlService.submit(requestContext, cmd);
        return success("jobId", jobId);
    }

    @RequestMapping("/queryLog")
    @ResponseBody
    public ModelMap queryLog(@RequestParam String jobId) {
        List<ExecutionLog> logs = executionLogService.queryLogsByJobIds(jobId);
        return success("logs", logs);
    }

    @RequestMapping("/exec/task")
    public ModelMap exec(String content) {
        String submittedPath = zkClientService.getZkPath4SubmittedJobs();
        String jobId = UUIDUtil.getUniqueId().toString();
        String subPath = submittedPath + "/" + jobId;
        ZkData zkData = new ZkData();
        zkData.setJobId(jobId);
        zkData.setData(content.getBytes(StandardCharsets.UTF_8));
        zkData.setGmtCreate(new Date());
        zkData.setPath(subPath);
        zkData.setRoot("/root");
        zkClientService.create(
                subPath,
                zkData,
                CreateMode.EPHEMERAL);
        return success("success", true);
    }
}
