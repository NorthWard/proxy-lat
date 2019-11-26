package com.north.lat.proxylat.service.impl.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CoreAspetJ {

    /**
     *  只有标注了@NeedMonitor 注解的方法才需要打印执行时间
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* *(..)) &&  @annotation(com.north.lat.proxylat.service.impl.aspectj.NeedMonitor)")
    public Object printCost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long bt = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long et = System.currentTimeMillis();
        System.out.println(proceedingJoinPoint.getSignature() + " cost: " + (et - bt));
        return object;
    }
}
