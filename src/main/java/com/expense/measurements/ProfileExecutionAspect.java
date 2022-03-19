package com.expense.measurements;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class ProfileExecutionAspect {
    private static final Logger logger = LoggerFactory.getLogger(ProfileExecutionAspect.class);

    @Around("@annotation(ProfileExecution)")
    public Object profileExecuteMethod(ProceedingJoinPoint jointPoint) throws Throwable {
        Signature signature = jointPoint.getSignature();
        String methodName = signature.toShortString();
        StopWatch stopWatch = new StopWatch(ProfileExecutionAspect.class.getName());
        stopWatch.start(methodName);
        Object returnedValue = jointPoint.proceed();
        stopWatch.stop();
        System.err.println("MEAS: " + stopWatch.getLastTaskName() + ": " + stopWatch.getLastTaskTimeMillis() + "ms");
        return returnedValue;
    }
}
