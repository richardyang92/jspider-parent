package com.ry.jspider.core.task;

import com.ry.jspider.core.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskFilterChain implements Filter
{
    private static Log log = Log.getLogger(TaskFilterChain.class);
    private List<Filter> filters = new ArrayList<Filter>();
    private int index = 0;

    public TaskFilterChain addFilter(Filter filter)
    {
        this.filters.add(filter);
        return this;
    }

    public void doFilter(Task task, Result result, TaskFilterChain chain)
    {
        if (this.index == this.filters.size()) {
            return;
        }
        Filter filter = this.filters.get(this.index);
        this.index += 1;
        filter.doFilter(task, result, chain);
    }
}
