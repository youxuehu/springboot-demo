package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(org.example.aop.Log)")
    public void logPointCut(){}

    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handlerLog(joinPoint, null);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handlerLog(joinPoint, e);
    }

    private void handlerLog(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log log = null;
        if (method != null) {
            log = method.getAnnotation(Log.class);
        }
        if (log == null) {
            return;
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String title = log.title();
        String function = log.function();
        System.out.println("className  >>>>>>  " + className);
        System.out.println("methodName  >>>>>>  " + methodName);
        System.out.println("title  >>>>>>  " + title);
        System.out.println("function  >>>>>>  " + function);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String dateTimeFormat = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println("dateTime  >>>>>>  " + dateTimeFormat);

        Object[] joinPointArgs = joinPoint.getArgs();
        System.out.println(joinPointArgs);

    }

}
