package com.ry.jspider.html.filter;

import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/22.
 */
public class JSoupFilter extends TaskFilter {
    private static Log log = Log.getLogger(JSoupFilter.class);
    public JSoupFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        if (task.getAttributes().get("html") == null
                || task.getAttributes().get("html").toString().trim().equals("")) {
            log.warn("html has no content");
        } else {
            Document document = Jsoup.parse(task.getAttributes().get("html").toString());
            task.getAttributes().remove("html");
            task.getAttributes().put("body", document.body());
        }
    }

    public void afterChain(Task task, Result result) {
        Element element = (Element) task.getAttributes().get("body");
        result.setResultString(element.toString());
    }
}
