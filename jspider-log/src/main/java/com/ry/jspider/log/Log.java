package com.ry.jspider.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangyang on 2016/12/21.
 */
public class Log {
    private Logger logger;

    private Log(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    private Log(String loggerName) {
        this.logger = LoggerFactory.getLogger(loggerName);
    }

    public static Log getLogger(String loggerName) {
        return new Log(loggerName);
    }

    public static Log getLogger(Class<?> clazz) {
        return new Log(clazz);
    }

    public void debug(String msg) {
        this.logger.debug(msg);
    }

    public void debug(String format, Object... argArray) {
        this.logger.debug(format, argArray);
    }

    public void debug(String msg, Throwable t) {
        this.logger.debug(msg, t);
    }

    public void debug(Throwable t) {
        this.logger.debug(t.getMessage(), t);
    }

    public void warn(String msg) {
        this.logger.warn(msg);
    }

    public void warn(String format, Object... argArray) {
        this.logger.warn(format, argArray);
    }

    public void warn(String msg, Throwable t) {
        this.logger.warn(msg, t);
    }

    public void warn(Throwable t) {
        this.logger.warn(t.getMessage(), t);
    }

    public void error(String msg) {
        this.logger.error(msg);
    }

    public void error(String format, Object... argArray) {
        this.logger.error(format, argArray);
    }

    public void error(String msg, Throwable t) {
        this.logger.error(msg, t);
    }

    public void error(Throwable t) {
        this.logger.error(t.getMessage(), t);
    }

    public void info(String msg) {
        this.logger.info(msg);
    }

    public void info(String format, Object... argArray) {
        this.logger.info(format, argArray);
    }

    public void info(String msg, Throwable t) {
        this.logger.info(msg, t);
    }

    public void info(Throwable t) {
        this.logger.info(t.getMessage(), t);
    }

    public void trace(String msg) {
        this.logger.trace(msg);
    }

    public void trace(String format, Object... argArray) {
        this.logger.trace(format, argArray);
    }

    public void trace(String msg, Throwable t) {
        this.logger.trace(msg, t);
    }

    public void trace(Throwable t) {
        this.logger.trace(t.getMessage(), t);
    }
}
