package com.ry.jspider.core.task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/21.
 */
public class Result {
    private String resultString;
    private Map<String, Object> resultMap = new HashMap<String, Object>();

    public Result(String resultString) {
        this.resultString = resultString;
    }

    public String getResultString() {
        return this.resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}
