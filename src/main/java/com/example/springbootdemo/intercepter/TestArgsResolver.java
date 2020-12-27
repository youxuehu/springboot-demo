package com.example.springbootdemo.intercepter;

import com.example.springbootdemo.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Service
public class TestArgsResolver implements HandlerMethodArgumentResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestArgsResolver.class);
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == TestVO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LOGGER.info(parameter.getMethod().getName());
        LOGGER.info(mavContainer.getViewName());
        LOGGER.info(webRequest.getContextPath());
        LOGGER.info(binderFactory.getClass().getName());
        LOGGER.info("<<<<<<<<");
        TestVO testVO = new TestVO();
        testVO.setMessage("测试TestVO成功");
        LOGGER.info("<<<<<<<<");
        return testVO;
    }
}
