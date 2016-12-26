package com.ry.jspider.test;

import com.ry.jspider.config.Const;
import com.ry.jspider.core.builder.TaskXMLBuilder;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskHandlerAdaptor;
import com.ry.jspider.core.task.TaskWorker;
import com.ry.jspider.selector.builder.DefaultSelectorBuilder;
import com.ry.jspider.test.selector.NextURLSelector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public class Test {
    private static String url =
            "http://search.51job.com/list/000000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    static {
        Const.CONFIG_BASE =
                "F:\\code\\jspider-parent\\jspider-test\\src\\main\\resources\\";
        Const.TASK_FILE = "craweler.xml";
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        Task task = TaskXMLBuilder.build(url, "worker1");

        TaskWorker worker = new TaskWorker("worker1");
        worker.setHandler(new TaskHandlerAdaptor());
        worker.init();

        Thread thread = new Thread(worker);
        thread.start();

        worker.submitTask(task);

    }
}
