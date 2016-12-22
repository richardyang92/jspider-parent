package com.ry.jspider.core.task;

/**
 * Created by yangyang on 2016/12/21.
 */
public interface Filter {
    void doFilter(Task task, Result result, TaskFilterChain chain);
}
