package com.ry.jspider.test;

import com.ry.jspider.config.Const;
import com.ry.jspider.config.CrawelerConfig;
import com.ry.jspider.config.XMLConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/21.
 */
public class ConfigUnitTest {

    static {
        Const.CONFIG_BASE =
                "F:\\code\\jspider-parent\\jspider-core\\src\\test\\java\\com\\ry\\jspider\\test\\";
        Const.TASK_FILE = "craweler.xml";
    }

    @Test
    public void TestSpiderName() {
        String spiderName = XMLConfig.loadConfig().getString(Const.SPIDER_NAME,
                CrawelerConfig.class, null, null);

        Assert.assertEquals("false", "test", spiderName);
    }

    @Test
    public void TestWorkerHost() {
        String host = XMLConfig.loadConfig().getString(Const.TASK_WORKER_HOST,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "127.0.0.1", host);
    }

    @Test
    public void TestWorkerPort() {
        int port = XMLConfig.loadConfig().getInt(Const.TASK_WORKER_PORT,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 38888L, port);
    }

    @Test
    public void TestFilters() {
        List<Map<String, String>> resultList = XMLConfig.loadConfig().getList(Const.FILTERS,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        List<Map<String, String>> expList = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "htmlRead");
        map.put("selectors", "false");
        map.put("class", "com.ry.jspider.test.HtmlReadFilter");
        expList.add(map);
        Assert.assertEquals("false", expList, resultList);
    }

    @Test
    public void TestConnectionRequestTimeout() {
        int connectionRequestTimeout = XMLConfig.loadConfig().getInt(Const.CONNECTION_REQUEST_TIMEOUT,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, connectionRequestTimeout);
    }

    @Test
    public void TestConnectTimeout() {
        int connectTimeout = XMLConfig.loadConfig().getInt(Const.CONNECT_TIMEOUT,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, connectTimeout);
    }

    @Test
    public void TestSocketTimeout() {
        int socketTimeout = XMLConfig.loadConfig().getInt(Const.SOCKET_TIMEOUT,
                CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", 500L, socketTimeout);
    }

    @Test
    public void TestWorkerNumber() {
        int workerNumber = XMLConfig.loadConfig().getInt(Const.TASK_WORKER_NUMBER,
                CrawelerConfig.class, null, null);

        Assert.assertEquals("false", 1L, workerNumber);
    }

    @Test
    public void TestAccept() {
        String accept = XMLConfig.loadConfig().getString(Const.ACCEPT, CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "Accept text/com.ry.jspider.core.html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8", accept);
    }

    @Test
    public void TestConnection() {
        String connection = XMLConfig.loadConfig().getString(Const.CONNECTION, CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "keep-alive", connection);
    }

    @Test
    public void TestUser_Agent() {
        String userAgent = XMLConfig.loadConfig().getString(Const.USER_AGENT_VALUE, CrawelerConfig.class, new Class[]{String.class}, new Object[]{"worker1"});

        Assert.assertEquals("false", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2", userAgent);
    }

    @Test
    public void TestSelectorList() {
        List<String> expList = new ArrayList<String>();
        expList.add("test.xml");

        List<String> result = XMLConfig.loadConfig().getList("SelectorList", CrawelerConfig.class, null, null);
        Assert.assertEquals("false", expList, result);
    }
}
