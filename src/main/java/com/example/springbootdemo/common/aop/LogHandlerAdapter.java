package com.example.springbootdemo.common.aop;

import com.example.common.anno.Log;
import com.example.common.db.dao.adminoperatelog.mapper.AdminOperateLogMapper;
import com.example.common.db.dao.adminoperatelog.model.AdminOperateLog;
import com.example.common.utils.holder.SessionInfoHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * MethodBeforeAdvice     前置的额外功能
 * 	AfterReturningAdvice    后置的额外功能
 * 	MethodInterceptor        环绕的额外功能
 */
@Component
public class LogHandlerAdapter implements MethodInterceptor {

    @Autowired
    AdminOperateLogMapper adminOperateLogMapper;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        String value = annotation.value();
        AdminOperateLog adminOperateLog = new AdminOperateLog();
        adminOperateLog.setContent(value);
        adminOperateLog.setOperator(SessionInfoHolder.get() == null ?
                "系统自动" : SessionInfoHolder.get().getUserName());
        adminOperateLog.setGmtCreate(new Date());
        adminOperateLogMapper.insertSelective(adminOperateLog);
        Object result = methodInvocation.proceed();
        return result;
    }

}
