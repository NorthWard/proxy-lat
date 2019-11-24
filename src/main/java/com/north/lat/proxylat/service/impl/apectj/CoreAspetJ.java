package com.north.lat.proxylat.service.impl.apectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CoreAspetJ {

    @Around("execution(* *(..)) &&  @annotation(com.north.lat.proxylat.service.impl.apectj.NeedMonitor)")
    public Object printCost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long bt = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long et = System.currentTimeMillis();
        System.out.println(proceedingJoinPoint.getSignature() + " cost: " + (et - bt));

        return object;
    }
}
