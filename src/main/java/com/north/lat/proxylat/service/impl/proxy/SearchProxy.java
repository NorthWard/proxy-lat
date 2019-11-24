package com.north.lat.proxylat.service.impl.proxy;

import com.north.lat.proxylat.service.Search;

public class SearchProxy implements Search {
    private Search search;
    public SearchProxy(Search search){
        this.search = search;
    }

    public String search(String key) {
        long bt = System.currentTimeMillis();
        String ret = search.search(key);
        long et = System.currentTimeMillis();
        System.out.println("SearchProxy.search cost : " + (et - bt));
        return ret;
    }
}
