package org.example.home.test;

import com.alipay.sofa.rpc.common.json.JSON;
import org.example.PredictFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/predict")
public class PredictController {

    Logger logger = LoggerFactory.getLogger(PredictController.class);

    @Autowired
    PredictFacade predictFacade;

    @RequestMapping("exec")
    @ResponseBody
    public ModelMap exec(HttpServletRequest request, Long id, String workName) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("workName", workName);
        if (logger.isInfoEnabled()) {
            logger.info(JSON.toJSONString(param, true));
        }
        String result = predictFacade.predict(param);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("param", param);
        modelMap.addAttribute("result", result);
        return modelMap;
    }
}
