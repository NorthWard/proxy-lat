package com.north.lat.proxylat.service.impl.jdkproxy;

import com.north.lat.proxylat.service.Search;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SearchInvocationHandler implements InvocationHandler{

    private Search target;
    public SearchInvocationHandler(Search target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long bt = System.currentTimeMillis();
        String ret = (String) method.invoke(target, args);
        long et = System.currentTimeMillis();
        System.out.println("SearchJdkProxy.search  cost : " + (et - bt));
        return ret;
    }


    public Search getJdkProxy() {
        return (Search) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Search.class}, this);
    }
}
