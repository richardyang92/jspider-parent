package com.ry.jspider.core.task;

import com.ry.jspider.core.log.Log;

/**
 * Created by yangyang on 2016/12/21.
 */
public abstract class TaskFilter implements Filter {
    private static Log log = Log.getLogger(TaskFilter.class);
    protected String name;
    protected String regExp;

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public TaskFilter(String name) {
        this.name = name;
    }

    public void doFilter(Task task, Result result, TaskFilterChain chain) {
        log.info("{} do filter", this.name);
        beforeChain(task, result);
        chain.doFilter(task, result, chain);
        afterChain(task, result);
    }

    public abstract void beforeChain(Task task, Result result);

    public abstract void afterChain(Task task, Result result);

}
