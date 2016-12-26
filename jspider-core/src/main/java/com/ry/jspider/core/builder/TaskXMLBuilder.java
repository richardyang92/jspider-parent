package com.ry.jspider.core.builder;

import com.ry.jspider.config.Const;
import com.ry.jspider.config.CrawelerConfig;
import com.ry.jspider.config.XMLConfig;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.log.Log;
import com.ry.jspider.selector.annotation.Selector;
import com.ry.jspider.selector.factory.SelectorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
        List<Map<String, Object>> filters = XMLConfig.loadConfig().getList(Const.FILTERS,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{taskWorkerId});

        log.debug("filters->size {}", filters.size());

        TaskFilter[] taskFilters = new TaskFilter[filters.size()];
        int index = 0;
        for (Map<String, Object> map : filters) {
            try {
                log.debug("map->name: {}, map->class: {}", map.get("name"), map.get("class"));
                Class clazz = Class.forName((String) map.get("class"));
                Constructor method = clazz.getConstructor(String.class);
                Object filter = method.newInstance(map.get("name"));

                if (map.get("selectors").equals("true")) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getAnnotation(Selector.class) != null) {
                            field.setAccessible(true);
                            String name = field.getType().getName();
                            Object selector = SelectorFactory.getInstance().getSelector(name);
                            log.info("selector->{}", name);
                            field.set(filter, selector);
                        }
                    }
                }
                taskFilters[index++] = (TaskFilter) filter;
            } catch (ClassNotFoundException e) {
                log.error("builder 1 {}", e.getMessage());
            } catch (NoSuchMethodException e) {
                log.error("builder 2 {}", e.getMessage());
            } catch (IllegalAccessException e) {
                log.error("builder 3 {}", e.getMessage());
            } catch (InstantiationException e) {
                log.error("builder 4 {}", e.getMessage());
            } catch (InvocationTargetException e) {
                log.error("builder 5 {}", e.getMessage());
            }
        }

        for (TaskFilter taskFilter : taskFilters) {
            log.debug("taskFilter: {}", taskFilter);
        }

        task.builtFilterChain(taskFilters);
        task.getAttributes().put("id", taskWorkerId);
        return task;
    }
}
