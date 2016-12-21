package com.ry.jspider.core.config;

import com.ry.jspider.core.log.Log;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyang on 2016/12/21.
 */
public class XMLConfig {
    private static Log log = Log.getLogger(XMLConfig.class);
    private static XMLConfig xmlConfig = null;
    protected Document document;

    public static XMLConfig loadConfig() {
        if (xmlConfig == null) {
            xmlConfig = new XMLConfig();
        }
        try {
            InputStream inputStream = new FileInputStream(new File(Const.CONFIG_FILE_PATH));
            SAXReader saxReader = new SAXReader();
            xmlConfig.document = saxReader.read(inputStream);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            xmlConfig.document = null;
        } catch (DocumentException e) {
            log.error(e.getMessage());
            xmlConfig.document = null;
        }
        return xmlConfig;
    }

    public String getString(String key, Class<? extends XMLConfig> clazz, Class<?>[] params, Object[] values) {
        String value = "";
        String methodName = "get" + key;
        try {
            if (params == null) {
                Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
                value = (String) method.invoke(null, new Object[0]);
            } else {
                Method method = clazz.getDeclaredMethod(methodName, params);
                value = (String) method.invoke(null, values);
            }
        } catch (IllegalAccessException e) {
            log.error("2 {}", new Object[]{e.getMessage()});
        } catch (NoSuchMethodException e) {
            log.error("3 {}", new Object[]{e.getMessage()});
        } catch (InvocationTargetException e) {
            log.error("4 {}", new Object[]{e.getMessage()});
        }
        return value;
    }

    public int getInt(String key, Class<? extends XMLConfig> clazz, Class<?>[] params, Object[] values) {
        int value = 0;
        String methodName = "get" + key;
        try {
            if (params == null) {
                Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
                value = ((Integer) method.invoke(null, new Object[0])).intValue();
            } else {
                Method method = clazz.getDeclaredMethod(methodName, params);
                value = ((Integer) method.invoke(null, values)).intValue();
            }
        } catch (IllegalAccessException e) {
            log.error("2 {}", new Object[]{e.getMessage()});
        } catch (NoSuchMethodException e) {
            log.error("3 {}", new Object[]{e.getMessage()});
        } catch (InvocationTargetException e) {
            log.error("4 {}", new Object[]{e.getMessage()});
        }
        return value;
    }

    public List getList(String key, Class<? extends XMLConfig> clazz, Class<?>[] params, Object[] values) {
        List value = new ArrayList();
        String methodName = "get" + key;
        try {
            if (params == null) {
                Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
                value = (List) method.invoke(null, new Object[0]);
            } else {
                Method method = clazz.getDeclaredMethod(methodName, params);
                value = (List) method.invoke(null, values);
            }
        } catch (IllegalAccessException e) {
            log.error("2 {}", new Object[]{e.getMessage()});
        } catch (NoSuchMethodException e) {
            log.error("3 {}", new Object[]{e.getMessage()});
        } catch (InvocationTargetException e) {
            log.error("4 {}", new Object[]{e.getMessage()});
        }
        return value;
    }
}
