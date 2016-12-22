package com.ry.jspider.core.html.filter;

import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by yangyang on 2016/12/22.
 */
public class JSoupFilter extends TaskFilter {
    private static Log log = Log.getLogger(JSoupFilter.class);
    public JSoupFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        if (task.getAttributes().get("com/ry/jspider/core/html") == null
                || task.getAttributes().get("com/ry/jspider/core/html").toString().trim().equals("")) {
            log.warn("com.ry.jspider.core.html has no content");
        } else {
            Document document = Jsoup.parse(task.getAttributes().get("com/ry/jspider/core/html").toString());
            task.getAttributes().remove("com/ry/jspider/core/html");
            task.getAttributes().put("body", document.body());
        }
    }

    public void afterChain(Task task, Result result) {
        Element element = (Element) task.getAttributes().get("body");
        result.setResultString(element.toString());
    }
}
