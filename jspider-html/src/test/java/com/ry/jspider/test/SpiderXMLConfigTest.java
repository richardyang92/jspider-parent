package com.ry.jspider.test;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.config.SpiderXMLConfig;
import com.ry.jspider.core.config.XMLConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/21.
 */
public class SpiderXMLConfigTest {
    @Test
    public void TestSpiderName() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        String spiderName = XMLConfig.loadConfig().getString(Const.SPIDER_NAME,
                SpiderXMLConfig.class, null, null);

        Assert.assertEquals("false", "test", spiderName);
    }

    @Test
    public void TestWorkerHost() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        String host = XMLConfig.loadConfig().getString(Const.TASK_WORKER_HOST,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "127.0.0.1", host);
    }

    @Test
    public void TestWorkerPort() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        int port = XMLConfig.loadConfig().getInt(Const.TASK_WORKER_PORT,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 38888L, port);
    }

    @Test
    public void TestFilters() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        List<Map<String, String>> resultList = XMLConfig.loadConfig().getList(Const.FILTERS,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        List<Map<String, String>> expList = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "loginFilter");
        map.put("class", "com.ry.jspider.html.BasicLoginFilter");
        expList.add(map);

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "htmlReadFilter");
        map1.put("class", "com.ry.jspider.html.HtmlReadFilter");
        expList.add(map1);
        Assert.assertEquals("false", expList, resultList);
    }

    @Test
    public void TestConnectionRequestTimeout() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        int connectionRequestTimeout = XMLConfig.loadConfig().getInt(Const.CONNECTION_REQUEST_TIMEOUT,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, connectionRequestTimeout);
    }

    @Test
    public void TestConnectTimeout() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        int connectTimeout = XMLConfig.loadConfig().getInt(Const.CONNECT_TIMEOUT,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, connectTimeout);
    }

    @Test
    public void TestSocketTimeout() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        int socketTimeout = XMLConfig.loadConfig().getInt(Const.SOCKET_TIMEOUT,
                SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, socketTimeout);
    }

    @Test
    public void TestWorkerNumber() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        int workerNumber = XMLConfig.loadConfig().getInt(Const.TASK_WORKER_NUMBER,
                SpiderXMLConfig.class, null, null);

        Assert.assertEquals("false", 1L, workerNumber);
    }

    @Test
    public void TestAccept() {
        String accept = XMLConfig.loadConfig().getString(Const.ACCEPT, SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8", accept);
    }

    @Test
    public void TestConnection() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        String connection = XMLConfig.loadConfig().getString(Const.CONNECTION, SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "keep-alive", connection);
    }

    @Test
    public void TestUser_Agent() {
        Const.CONFIG_FILE_PATH = "F:\\code\\jspider-parent\\jspider-html\\src\\main\\resources\\spider.xml";
        String userAgent = XMLConfig.loadConfig().getString(Const.USER_AGENT_VALUE, SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2", userAgent);
    }
}
