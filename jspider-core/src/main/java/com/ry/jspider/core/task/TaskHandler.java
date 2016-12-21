package com.ry.jspider.core.task;

import com.ry.jspider.core.log.Log;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskHandler {
    private static Log log = Log.getLogger(TaskHandler.class);

    public void messageReceived(String result) {
        if (result.equals("")) {
            log.info("result is null");
        } else {
            log.info("messageReceived message: {}", result);
        }
    }

    public void exceptionCaught(Throwable t) {
        log.error("get result failed, error is {}", t.getMessage());
    }
}
