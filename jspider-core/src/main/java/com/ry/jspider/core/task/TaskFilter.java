package com.ry.jspider.core.task;

import com.ry.jspider.log.Log;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskFilter implements Filter {
    private static Log log = Log.getLogger(TaskFilter.class);
    protected String name;

    public TaskFilter(String name) {
        this.name = name;
    }

    public void doFilter(Task task, Result result, TaskFilterChain chain) {
        log.info("{} do filter", this.name);
        beforeChain(task, result);
        chain.doFilter(task, result, chain);
        afterChain(task, result);
    }

    public void beforeChain(Task task, Result result) {
        log.info("{} before chain", this.name);
    }

    public void afterChain(Task task, Result result) {
        log.info("{} after chain", this.name);
    }

}
