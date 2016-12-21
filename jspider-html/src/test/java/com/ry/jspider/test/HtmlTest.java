package com.ry.jspider.test;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskWorker;
import com.ry.jspider.html.filter.HtmlReadFilter;

/**
 * Created by yangyang on 2016/12/20.
 */
public class HtmlTest {
    private static Log log = Log.getLogger(HtmlTest.class);
    public static void main(String[] args) throws InterruptedException {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        TaskWorker worker = new TaskWorker("worker1");
        worker.init();

        Thread thread = new Thread(worker);
        thread.start();
        Task task = new Task("http://www.1kkk.com/other148716/");
        task.builtFilterChain(new HtmlReadFilter("baiduFilter"));
        worker.submitTask(task);

        Thread.sleep(10000);
        worker.destroy();
    }
}
