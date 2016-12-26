package com.ry.jspider.test.selector;

import com.ry.jspider.selector.annotation.Param;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public interface NextURLSelector {
    Elements getNextURL(@Param("html")String html);
}
