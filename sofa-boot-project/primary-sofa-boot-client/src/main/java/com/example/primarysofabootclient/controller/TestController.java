package com.example.primarysofabootclient.controller;

import com.alipay.hessian.generic.model.GenericObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.common.json.JSON;
import com.example.primarysofabootclient.controller.param.ResponseVO;
import org.example.service.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private GenericService testGenericServiceReference;

    @GetMapping("/request")
    public String request() {
        ResponseVO responseVO = new ResponseVO();

        // 泛化调用

        GenericObject genericObject = new GenericObject("org.example.service.RequestVO");

        genericObject.putField("name", "游学虎");
        genericObject.putField("age", 26);
        genericObject.putField("email", "youxuehu@hotmail.com");

        ResultVO result = (ResultVO) testGenericServiceReference.$genericInvoke("request",
                new String[] { "org.example.service.RequestVO" },
                new Object[] { genericObject }, ResultVO.class);

//        Boolean success = (Boolean) result.getField("success");
//        String code = (String) result.getField("code");
//        String data = (String) result.getField("data");
//        String errorMessage = (String) result.getField("errorMessage");

//        responseVO.setCode(code);
//        responseVO.setData(data);
//        responseVO.setErrorMessage(errorMessage);
//        responseVO.setSuccess(success);
        BeanUtils.copyProperties(result, responseVO);
        return JSON.toJSONString(responseVO, true);
    }
}
