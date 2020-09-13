package org.example.aop;

import org.example.beans.UserService;
import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

/**
 * AOP 功能 MethodBeforeAdvice （前置）
 *         AfterReturningAdvice（后置）
 *         MethodInterceptor（环绕）
 */
public class MethodBeforeHandlerAOP implements MethodBeforeAdvice {

    /**
     * 前置额外功能
     * @param method // 执行的方法
     * @param objects // 执行的参数
     * @param o // 目标
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        System.out.println("<<<<<<<<<<< 调用额外功能 MethodHandlerAOP >>>>>>>>>>>>>");
        UserService userService = (UserService) o;
        String result = userService.sayHello("jack");
        System.out.println(result);
    }

    public void execAdvice() {
        System.out.println("<<<<<<<<<<<<<<  exec  execAdviee ~~  >>>>>>>>>>>>");
    }
}
