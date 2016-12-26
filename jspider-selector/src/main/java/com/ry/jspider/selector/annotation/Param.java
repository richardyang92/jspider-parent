package com.ry.jspider.selector.annotation;

import java.lang.annotation.*;

/**
 * Created by yangyang on 2016/12/24.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Param {
    public String value();
}
