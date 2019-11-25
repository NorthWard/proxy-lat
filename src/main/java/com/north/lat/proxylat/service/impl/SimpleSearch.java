package com.north.lat.proxylat.service.impl;

import com.north.lat.proxylat.service.Search;
import com.north.lat.proxylat.service.impl.aspectj.NeedMonitor;

public class SimpleSearch implements Search {

    @NeedMonitor
    @Override
    public String search(String key) {
        return  key.hashCode() + "";
    }

    @NeedMonitor
    public String prettySearch(String key){
       String result = search(key);
       // 优化一下返回结果
       result = result.replace("-","");
       return result;
    }
}
