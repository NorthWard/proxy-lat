package com.north.lat.proxylat.main;


import com.north.lat.proxylat.service.Search;
import com.north.lat.proxylat.service.impl.SimpleSearch;
import com.north.lat.proxylat.service.impl.cglib.CglibProxy;
import com.north.lat.proxylat.service.impl.javaassist.JavassistProxy;
import com.north.lat.proxylat.service.impl.jdkproxy.SearchInvocationHandler;
import com.north.lat.proxylat.service.impl.proxy.SearchProxy;

import java.lang.reflect.Proxy;

public class SearchSomething {
    public static void main(String[] args) {
        SimpleSearch search = new SimpleSearch();
        String key = "keyWord";

/*        JavassistProxy javassistProxy = JavassistProxy.getInstance();

        javassistProxy.prettySearch(key);
        javassistProxy.search(key);*/
        while(true){
            System.out.println("SimpleSearch.prettySearch : " + search.prettySearch(key));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }


        // 代理模式
/*        SearchProxy searchProxy = new SearchProxy(search);
        System.out.println("searchProxy search : " + searchProxy.search(key));*/

        // JDK动态代理模式
/*        SearchInvocationHandler searchInvocationHandler = new SearchInvocationHandler(search);
        Search jdkProxy = searchInvocationHandler.getJdkProxy();
        System.out.println("jdkProxy search : " + jdkProxy.search(key));*/


        // CGLIB 代理模式
/*        CglibProxy cglibProxy = new CglibProxy(search);
        System.out.println("cglibProxy search : " + cglibProxy.getCglibProxy().prettySearch(key));*/


    }

}
