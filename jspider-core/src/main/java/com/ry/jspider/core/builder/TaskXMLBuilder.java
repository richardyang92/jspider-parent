package com.ry.jspider.core.builder;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.config.CrawelerConfig;
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

    public TaskXMLBuilder() {
    }

    public static Task build(String taskURL, String taskWorkerId) {
        Task task = new Task(taskURL);
        List<Map<String, String>> filters = XMLConfig.loadConfig().getList(Const.FILTERS,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{taskWorkerId});

        log.info("filters->size {}", filters.size());

        TaskFilter[] taskFilters = new TaskFilter[filters.size()];
        int index = 0;
        for (Map<String, String> map : filters) {
            try {
                log.info("map->name: {}, map->class: {}", map.get("name"), map.get("class"));
                Class clazz = Class.forName((String) map.get("class"));
                Constructor method = clazz.getConstructor(String.class);
                TaskFilter filter = (TaskFilter) method.newInstance(map.get("name"));
                filter.setRegExp(map.get("regExp"));
                taskFilters[index++] = filter;
            } catch (ClassNotFoundException e) {
                log.error("1 {}", e.getMessage());
            } catch (NoSuchMethodException e) {
                log.error("2 {}", e.getMessage());
            } catch (IllegalAccessException e) {
                log.error("3 {}", e.getMessage());
            } catch (InstantiationException e) {
                log.error("4 {}", e.getMessage());
            } catch (InvocationTargetException e) {
                log.error("5 {}", e.getMessage());
            }
        }

        for (TaskFilter taskFilter : taskFilters) {
            log.info("taskFilter: {}", taskFilter);
        }

        task.builtFilterChain(taskFilters);
        task.getAttributes().put("id", taskWorkerId);
        return task;
    }
}
