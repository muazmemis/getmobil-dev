package dev.muazmemis.getmobil_dev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Aspect
@Component
public class MethodLoggerAspect {

    @Pointcut("execution(public * dev.muazmemis.getmobil_dev.service..*(..))")
    public void allPublicMethodsInGetMobil() {
    }

    @Around("allPublicMethodsInGetMobil()")
    public Object methodLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature methodSignature = joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String className = methodSignature.toShortString().split("\\.")[0];

        logInfo("{}::{}: start", className, methodName);
        Object result = joinPoint.proceed();
        logInfo("{}::{}: finish", className, methodName);
        return result;
    }

}
