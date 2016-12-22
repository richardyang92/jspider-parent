package com.ry.jspider.html.filter;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.html.util.HttpUtil;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyang on 2016/12/20.
 */
public class HtmlReadFilter extends TaskFilter {
    private static Log log = Log.getLogger(HtmlReadFilter.class);

    public HtmlReadFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        String url = task.getTaskURL();
        String html = "";

        if (judgeURL(url)) {
            try {
                html = HttpUtil.getHtml(url, (String) task.getAttributes().get(Const.WORKER_ID));
                Map<String, Object> attributes = task.getAttributes();
                attributes.put("html", html);
            } catch (IOException e) {
                html = "";
                log.error(e.getMessage());
            }
        } else {
            log.warn("url is invalidate");
        }
    }

    public void afterChain(Task task, Result result) {
//        Map<String, Object> attributes = task.getAttributes();
//        result.setResultString(attributes.get("html").toString());
    }

    private boolean judgeURL(String url) {
        Pattern pattern = Pattern.compile(Const.URL_PATTERN);
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }
}
