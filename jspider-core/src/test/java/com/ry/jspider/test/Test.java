package com.ry.jspider.test;

import com.ry.jspider.core.builder.TaskXMLBuilder;
import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskHandlerAdaptor;
import com.ry.jspider.core.task.TaskWorker;

/**
 * Created by yangyang on 2016/12/21.
 */
public class Test {
    static {
        Const.CONFIG_FILE_PATH =
                "F:\\code\\jspider-parent\\jspider-core\\src\\test\\java\\com\\ry\\jspider\\test\\craweler.xml";
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = TaskXMLBuilder.build("http://www.baidu.com", "worker1");

        TaskWorker worker = new TaskWorker("worker1");
        worker.setHandler(new TaskHandlerAdaptor());
        worker.init();

        Thread thread = new Thread(worker);
        thread.start();

        worker.submitTask(task);

        Thread.sleep(10000);
        worker.destroy();
    }
}
