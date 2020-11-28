package org.example.impl;

import com.alipay.sofa.rpc.common.json.JSON;
import org.example.PredictFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PredictFacadeImpl implements PredictFacade {
    Logger logger = LoggerFactory.getLogger(PredictFacadeImpl.class);

    @Override
    public String predict(Map<String, String> params) {
        if (logger.isInfoEnabled()) {
            logger.info(JSON.toJSONString(params, true));
        }
        return "predict success";
    }
}
