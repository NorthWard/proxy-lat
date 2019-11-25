package com.north.lat.proxylat.service.impl.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler{

    /**
     * target  一般指委托类实例
     */
    private Object target;
    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long bt = System.currentTimeMillis();
        // 通过调用target的method
        String ret = (String) method.invoke(target, args);
        long et = System.currentTimeMillis();
        System.out.println("SearchJdkProxy.search  cost : " + (et - bt));
        return ret;
    }
}
