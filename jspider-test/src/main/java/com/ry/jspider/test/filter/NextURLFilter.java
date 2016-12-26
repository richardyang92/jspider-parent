package com.ry.jspider.test.filter;

import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.log.Log;
import com.ry.jspider.selector.annotation.Selector;
import com.ry.jspider.test.selector.FindResultSelector;
import com.ry.jspider.test.selector.NextURLSelector;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public class NextURLFilter extends TaskFilter {
    private static Log log = Log.getLogger(NextURLFilter.class);

    @Selector
    private NextURLSelector nextURLSelector;

    @Selector
    private FindResultSelector findResultSelector;

    public NextURLFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        String html = (String) task.getAttributes().get("html");
        Elements elements = nextURLSelector.getNextURL(html);
        task.getAttributes().put("elements", elements);
    }
}
