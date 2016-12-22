package com.ry.jspider.test;

import com.ry.jspider.core.spider.crawel.Craweler;

/**
 * Created by yangyang on 2016/12/22.
 */
public class CrawelerTest {
    public static void main(String[] args) {
        Craweler craweler = new Craweler();
        craweler.init();
        craweler.connect("127.0.0.1", 38888);
        craweler.send("{\"name\":\"worker1\"}");
        craweler.destroy();
    }
}
