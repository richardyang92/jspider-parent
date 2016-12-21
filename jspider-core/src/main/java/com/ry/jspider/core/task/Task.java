package com.ry.jspider.core.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by yangyang on 2016/12/21.
 */
public class Task implements Callable<String> {
    private String taskURL;
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private Future<String> resultFuture;
    private TaskFilterChain filterChain = new TaskFilterChain();

    public Task(String taskURL) {
        this.taskURL = taskURL;
    }

    public Task() {
    }

    public void builtFilterChain(Filter... filters) {
        for (Filter filter : filters) {
            this.filterChain.addFilter(filter);
        }
    }

    public String call()
            throws Exception {
        Result result = new Result("");
        this.filterChain.doFilter(this, result, this.filterChain);
        return result.getResultString();
    }

    public String getTaskURL() {
        return this.taskURL;
    }

    public void setTaskURL(String taskURL) {
        this.taskURL = taskURL;
    }

    public Future<String> getResultFuture() {
        return this.resultFuture;
    }

    public void setResultFuture(Future<String> resultFuture) {
        this.resultFuture = resultFuture;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public TaskFilterChain getFilterChain() {
        return this.filterChain;
    }

    public void setFilterChain(TaskFilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
