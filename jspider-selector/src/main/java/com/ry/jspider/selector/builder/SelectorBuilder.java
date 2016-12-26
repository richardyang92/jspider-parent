package com.ry.jspider.selector.builder;

/**
 * Created by yangyang on 2016/12/24.
 */
public abstract class SelectorBuilder {
    protected String xmlPath;

    public SelectorBuilder(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    protected abstract <T> T build(Class<T> clazz);

    protected abstract Object build(String name);
}
