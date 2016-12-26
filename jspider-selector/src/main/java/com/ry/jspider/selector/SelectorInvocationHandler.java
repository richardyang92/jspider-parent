package com.ry.jspider.selector;

import com.ry.jspider.log.Log;
import com.ry.jspider.selector.annotation.Param;
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyang on 2016/12/24.
 */
public class SelectorInvocationHandler implements InvocationHandler {
    private static Log log = Log.getLogger(SelectorInvocationHandler.class);

    private Element selectors;
    private List<SelectorBean> selectorBeanList;

    public SelectorInvocationHandler(Element selectors) {
        this.selectors = selectors;
        selectorBeanList = getSelectors();
    }

    private List<SelectorBean> getSelectors() {
        List<SelectorBean> list = new ArrayList<SelectorBean>();

        for (Object object : selectors.elements("select")) {
            Element selectorElement = (Element) object;
            SelectorBean selectorBean = new SelectorBean();
            selectorBean.setId(selectorElement.attributeValue("id"));
            selectorBean.setParamType(selectorElement.attributeValue("paramType"));
            selectorBean.setResultType(selectorElement.attributeValue("resultType"));
            selectorBean.setCssSelector(selectorElement.elementText("css-selector"));
            list.add(selectorBean);
        }
        return list;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (SelectorBean selectorBean : selectorBeanList) {
            String methodName = method.getName();
            if (selectorBean.getId().equals(methodName) && selectorBean.getParamType().equals("string")) {
//                Annotation[][] annotations = method.getParameterAnnotations();
//                Param param = (Param) annotations[0][0];
                Document document = Jsoup.parse((String) args[0]);
                return document.select(selectorBean.getCssSelector());
            }
        }
        return null;
    }
}
