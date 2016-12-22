package com.ry.jspider.core.html.url;

import com.ry.jspider.core.config.SpiderXMLConfig;
import com.ry.jspider.core.config.XMLConfig;
import com.ry.jspider.core.util.HttpUtil;

import java.util.List;

/**
 * Created by yangyang on 2016/12/22.
 */
public class URLManager {
    private List<String> seeds;

    private void sift() {
        List<String> urls = XMLConfig.loadConfig().getList("URLList", SpiderXMLConfig.class,
                null, null);

        for (String url : urls) {
            if (HttpUtil.judgeURL(url)) {
                seeds.add(url);
            }
        }
    }

    public URLManager() {
        sift();
    }
}
