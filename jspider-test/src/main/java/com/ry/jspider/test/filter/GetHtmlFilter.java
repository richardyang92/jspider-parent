package com.ry.jspider.test.filter;

import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.core.util.CharacterUtil;
import com.ry.jspider.core.util.HttpUtil;
import com.ry.jspider.log.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by yangyang on 2016/12/24.
 */
public class GetHtmlFilter extends TaskFilter {
    private static Log log = Log.getLogger(GetHtmlFilter.class);

    public GetHtmlFilter(String name) {
        super(name);
    }

    public void beforeChain(Task task, Result result) {
        String url = task.getTaskURL();

        try {
            String html = HttpUtil.getHtml(url, (String) task.getAttributes().get("id"));
            String utf8Html = CharacterUtil.gbk2UTF8(html);
//            log.info(CharacterUtil.detect(new ByteArrayInputStream(html.getBytes())));
            task.getAttributes().put("html", utf8Html);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
