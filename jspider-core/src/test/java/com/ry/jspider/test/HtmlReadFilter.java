package com.ry.jspider.test;

import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.core.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by yangyang on 2016/12/22.
 */
public class HtmlReadFilter extends TaskFilter {
    private static Log log = Log.getLogger(HtmlReadFilter.class);

    public HtmlReadFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        String url = task.getTaskURL();
        if (HttpUtil.judgeURL(url)) {
            try {
                String html = HttpUtil.getHtml(url,
                        (String) task.getAttributes().get("id"));
                Document document = Jsoup.parse(html);
                log.info("document:\n{}", document);
                result.getResultMap().put("document", document);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void afterChain(Task task, Result result) {
    }
}
