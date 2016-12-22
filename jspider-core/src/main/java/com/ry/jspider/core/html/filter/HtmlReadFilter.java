package com.ry.jspider.core.html.filter;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.core.util.HttpUtil;

import java.io.IOException;
import java.util.Map;

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

        if (HttpUtil.judgeURL(url)) {
            try {
                html = HttpUtil.getHtml(url, (String) task.getAttributes().get(Const.WORKER_ID));
                Map<String, Object> attributes = task.getAttributes();
                attributes.put("com/ry/jspider/core/html", html);
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
//        result.setResultString(attributes.get("com.ry.jspider.core.html").toString());
    }
}
