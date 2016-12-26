package com.ry.jspider.selector.factory;

import com.ry.jspider.config.Const;
import com.ry.jspider.config.CrawelerConfig;
import com.ry.jspider.config.XMLConfig;
import com.ry.jspider.selector.builder.DefaultSelectorBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangyang on 2016/12/24.
 */
public class SelectorFactory {
    private static HashSet<String> configPathSet;
    private static SelectorFactory factory;

    private Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    static {
        List<String> urls = XMLConfig.loadConfig().getList(Const.SELECTOR_LIST,
                CrawelerConfig.class, null, null);
        System.out.println(urls.size());
        configPathSet = new HashSet<String>();

        for (String url : urls) {
            if (!configPathSet.contains(url)) {
                configPathSet.add(url);
            }
        }
    }

    private SelectorFactory() {
        for (String path : configPathSet) {
            String realFilePath = Const.CONFIG_BASE + path;
            System.out.println(realFilePath);
            DefaultSelectorBuilder builder = new DefaultSelectorBuilder(realFilePath);
            String name = builder.getSelectors().attributeValue("namespace");
            container.put(name, builder.build(name));
        }
    }

    public static SelectorFactory getInstance() {
        if (factory == null) {
            factory = new SelectorFactory();
        }
        return factory;
    }

    public Object getSelector(String name) {
        System.out.println(container);
        return container.get(name);
    }
}
