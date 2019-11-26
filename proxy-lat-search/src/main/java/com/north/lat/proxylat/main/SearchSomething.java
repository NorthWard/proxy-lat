package com.north.lat.proxylat.main;


import com.north.lat.proxylat.service.impl.SimpleSearch;

public class SearchSomething {
    public static void main(String[] args) throws InterruptedException {
        SimpleSearch search = new SimpleSearch();
        while (true){
            System.out.println("====begin===");
            String result = search.prettySearch("123");
            System.out.println("search result = [" + result + "]");
            System.out.println("====end===");
            Thread.sleep(3000);
        }

    }
}
