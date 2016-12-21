package com.ry.jspider.test;

import com.ry.jspider.html.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by yangyang on 2016/12/20.
 */
public class JSoupTest {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.parse(HttpUtil.getHtml("http://www.1kkk.com/other148716/", "worker1"));
        Elements newsHeadlines = doc.select("div[id=\"showimage\"]");
        System.out.println(newsHeadlines.html());
    }
}
