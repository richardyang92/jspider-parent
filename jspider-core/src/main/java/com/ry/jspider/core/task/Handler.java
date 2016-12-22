package com.ry.jspider.core.task;

/**
 * Created by yangyang on 2016/12/22.
 */
public interface Handler {
    void messageReceived(String result);
    void exceptionCaught(Throwable t);
}
