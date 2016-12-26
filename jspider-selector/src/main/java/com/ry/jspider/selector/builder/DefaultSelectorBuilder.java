package com.ry.jspider.selector.builder;

import com.ry.jspider.selector.SelectorInvocationHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Proxy;

/**
 * Created by yangyang on 2016/12/24.
 */
public class DefaultSelectorBuilder extends SelectorBuilder {
    private Element selectors;

    public Element getSelectors() {
        return selectors;
    }

    public void setSelectors(Element selectors) {
        this.selectors = selectors;
    }

    public DefaultSelectorBuilder(String xmlPath) {
        super(xmlPath);
        try {
            this.selectors = loadConfig(xmlPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private Element loadConfig(String xmlPath) throws FileNotFoundException, DocumentException {
        InputStream inputStream = new FileInputStream(new File(xmlPath));
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        return document.getRootElement();
    }

    public<T> T build(Class<T> clazz) {
        SelectorInvocationHandler handler = new SelectorInvocationHandler(selectors);

        return  (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] {clazz}, handler);
    }

    public Object build(String name) {
        try {
            return build(Class.forName(name));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
