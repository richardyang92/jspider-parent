package com.ry.jspider.test;

import com.ry.jspider.selector.builder.DefaultSelectorBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/24.
 */
public class Test {
    public static void main(String[] args) {
        DefaultSelectorBuilder builder =
                new DefaultSelectorBuilder("F:\\code\\jspider-parent\\jspider-selector\\src\\test\\java\\com\\ry\\jspider\\test\\selector.xml");
        TestSelector selector = builder.build(TestSelector.class);
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>canvas</title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\">\n" +
                "        body{\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        header[class$=\"title\"] {\n" +
                "            color: white;\n" +
                "            width: auto;\n" +
                "            height: 25px;\n" +
                "            border-radius: 5px;\n" +
                "            padding: 5px;\n" +
                "            margin: 2px;\n" +
                "            background-color: #48525e;\n" +
                "        }\n" +
                "        .dashboard {\n" +
                "            font-family: Tahoma, Arial, sans-serif;\n" +
                "            font-size: 20px;\n" +
                "            float: left;\n" +
                "        }\n" +
                "        .dashboard:hover {\n" +
                "            font-family: Tahoma, Arial, sans-serif;\n" +
                "            font-size: 20px;\n" +
                "            float: left;\n" +
                "            cursor:pointer;\n" +
                "            color: aqua;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<header class=\"title\">\n" +
                "    <section>\n" +
                "        <div class=\"dashboard\">我的博客</div>\n" +
                "        <div class=\"dashboard\">yangyang</div>\n" +
                "    </section>\n" +
                "</header>\n" +
                "</body>\n" +
                "</html>";
        Elements elements = selector.selectNextURL(html);
        for (Element element : elements) {
            System.out.println(element.html());
        }
    }
}
