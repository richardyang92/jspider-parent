package com.ry.jspider.core.util;

import com.ry.jspider.core.config.Const;
import com.ry.jspider.core.config.SpiderXMLConfig;
import com.ry.jspider.core.config.XMLConfig;
import com.ry.jspider.core.log.Log;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyang on 2016/12/20.
 */
public class HttpUtil {
    private static Log log = Log.getLogger(HttpUtil.class);
    private static HttpUtil httpUtil = null;

    private HttpUtil() {

    }

    public HttpUtil loadHttpClient() {
        if (httpUtil == null) {
            return new HttpUtil();
        }

        return httpUtil;
    }

    private static RequestConfig setRequestConfig(String id) {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(XMLConfig.loadConfig().getInt(Const.CONNECTION_REQUEST_TIMEOUT,
                        SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{id}))
                .setConnectTimeout(XMLConfig.loadConfig().getInt(Const.CONNECT_TIMEOUT,
                        SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{id}))
                .setSocketTimeout(XMLConfig.loadConfig().getInt(Const.SOCKET_TIMEOUT,
                        SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{id})).build();
    }

    private static void setGetHeader(HttpGet httpGet, String id) {
        httpGet.setHeader(Const.ACCEPT, XMLConfig.loadConfig().getString(Const.ACCEPT, SpiderXMLConfig.class,
                new Class[]{String.class}, new Object[]{id}));
        httpGet.setHeader(Const.CONNECTION, XMLConfig.loadConfig().getString(Const.CONNECTION, SpiderXMLConfig.class,
                new Class[]{String.class}, new Object[]{id}));
        httpGet.setHeader(Const.USER_AGENT_KEY, XMLConfig.loadConfig().getString(Const.USER_AGENT_VALUE, SpiderXMLConfig.class,
                new Class[]{String.class}, new Object[]{id}));
    }

    public static String getHtml(String url, String id) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        setGetHeader(httpGet, id);
        httpGet.setConfig(setRequestConfig(id));

        CloseableHttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        }
        httpGet.releaseConnection();
        return null;
    }

    public static boolean judgeURL(String url) {
        Pattern pattern = Pattern.compile(Const.URL_PATTERN);
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }
}
