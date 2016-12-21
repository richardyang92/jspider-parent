package com.ry.jspider.test;

import com.ry.jspider.core.builder.TaskXMLBuilder;
import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskWorker;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskXMLBuilderTest {

    public static void main(String[] args) throws InterruptedException {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        TaskXMLBuilder builder = new TaskXMLBuilder("http://www.baidu.com", "worker1");
        Task task = builder.build().getInstance();

        TaskWorker worker = new TaskWorker("worker1");
        worker.init();

        Thread thread = new Thread(worker);
        thread.start();

        worker.submitTask(task);

        Thread.sleep(10000);
        worker.destroy();
    }
}
