package com.north.lat.proxylat.service.impl.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long bt = System.currentTimeMillis();
        String ret = (String) method.invoke(target, objects);
        long et = System.currentTimeMillis();
        System.out.println("CglibProxy." + method.getName() + "  cost : " + (et - bt));
        return ret;
    }
}
