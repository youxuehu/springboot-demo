package com.example.primarysofabootclient.controller;

import com.alipay.hessian.generic.model.GenericObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.common.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private GenericService helloSyncGenericServiceReference;

    @GetMapping("/sayHello")
    public String sayHello(String name) {

//        GenericObject genericObject = new GenericObject("com.alipay.sofa.rpc.invoke.generic.TestObj");
//
//        genericObject.putField("str", "xxxx");
//        genericObject.putField("num", 222);
//
//        GenericObject result = (GenericObject) testService.$genericInvoke("echoObj",
//                new String[] { "com.alipay.sofa.rpc.invoke.generic.TestObj" },
//                new Object[] { genericObject });
//
//        String str = result.getField("str");
//        String num = result.getField("num");

        String result = (String) helloSyncGenericServiceReference.$genericInvoke("sayHello",
                new String[]{"java.lang.String"}, new Object[]{name});
        return JSON.toJSONString(result, true);
    }
}
