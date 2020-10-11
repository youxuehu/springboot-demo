package com.example.primarysofaboot.service.impl;

import com.alipay.sofa.rpc.common.json.JSON;
import org.example.service.RequestVO;
import org.example.service.ResultVO;
import org.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
    @Override
    public ResultVO request(RequestVO requestVO) {
        LOGGER.info("接收的参数：" + JSON.toJSONString(requestVO, true));
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setData(new Date());
        resultVO.setSuccess(true);
        return resultVO;
    }
}
