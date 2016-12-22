package com.ry.jspider.test;

import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import org.jsoup.nodes.Document;
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
        log.info("regExp: {}", regExp);
        Document document = (Document) result.getResultMap().get("document");
        Elements elements = document.select(regExp);
        log.info("elements size: {}", elements.size());
    }

    public void afterChain(Task task, Result result) {
        //
    }
}
