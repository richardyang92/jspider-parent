package com.ry.jspider.test.filter;

import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public class FindResultFilter extends TaskFilter {
    public FindResultFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        Elements elements = (Elements) task.getAttributes().get("elements");

        for (Element element : elements) {
            String elementString = element.html();
            if (elementString.equals("下一页")) {
                result.getResultMap().put("nextURL", element.attr("href"));
            }
        }
    }

    public void afterChain(Task task, Result result) {
        String nextURL = (String) result.getResultMap().get("nextURL");
        result.setResultString(nextURL);
    }
}
