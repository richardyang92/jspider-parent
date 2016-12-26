package com.ry.jspider.test;

import com.ry.jspider.selector.annotation.Param;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public interface TestSelector {
    Elements selectNextURL(@Param("html") String html);
}
