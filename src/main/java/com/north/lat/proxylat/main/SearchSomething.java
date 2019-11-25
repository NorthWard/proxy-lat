package com.north.lat.proxylat.main;


import com.north.lat.proxylat.service.impl.SimpleSearch;

public class SearchSomething {
    public static void main(String[] args) {
        SimpleSearch search = new SimpleSearch();
        String key = "keyWord";
        search.prettySearch(key);
    }
}
