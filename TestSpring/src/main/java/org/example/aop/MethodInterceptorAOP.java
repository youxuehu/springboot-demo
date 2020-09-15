package org.example.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodInterceptorAOP implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String className = invocation.getClass().getName();
        String methodName = invocation.getMethod().getName();
        System.out.println(className+ "." + methodName);
        System.out.println("<<<<<< before  invocation  [ " + invocation + "  ] >>>>>>>>>>>>>>");
        Method method = invocation.getMethod();
        Annotation[] annotations = method.getDeclaredAnnotations();

        System.out.println(annotations.length);

        Object proceed = invocation.proceed();
        System.out.println("proceed >>>>>>>>> " + proceed);
        return proceed;
    }
}
