package com.ry.jspider.core.builder;

import com.ry.jspider.core.config.SpiderXMLConfig;
import com.ry.jspider.core.config.XMLConfig;
import com.ry.jspider.core.log.Log;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskXMLBuilder {
    private static Log log = Log.getLogger(TaskXMLBuilder.class);
    private Task task;
    private String taskURL;
    private String taskWorkerId;

    public TaskXMLBuilder(String taskURL, String taskWorkerId) {
        this.taskURL = taskURL;
        this.taskWorkerId = taskWorkerId;
    }

    public TaskXMLBuilder build() {
        this.task = new Task(this.taskURL);
        List<Map<String, String>> filters = XMLConfig.loadConfig().getList("Filters", SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{this.taskWorkerId});

        TaskFilter[] taskFilters = new TaskFilter[filters.size()];
        int index = 0;
        for (Map<String, String> map : filters) {
            try {
                Class clazz = Class.forName((String) map.get("class"));
                Constructor method = clazz.getConstructor(new Class[]{String.class});
                TaskFilter filter = (TaskFilter) method.newInstance(new Object[]{map.get("name")});
                taskFilters[index] = filter;
            } catch (ClassNotFoundException e) {
                log.error("1 {}", new Object[]{e.getMessage()});
            } catch (NoSuchMethodException e) {
                log.error("2 {}", new Object[]{e.getMessage()});
            } catch (IllegalAccessException e) {
                log.error("3 {}", new Object[]{e.getMessage()});
            } catch (InstantiationException e) {
                log.error("4 {}", new Object[]{e.getMessage()});
            } catch (InvocationTargetException e) {
                log.error("5 {}", new Object[]{e.getMessage()});
            }
        }
        this.task.builtFilterChain(taskFilters);
        return this;
    }

    public Task getInstance() {
        return this.task;
    }
}
