package com.hqu.modules.craw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.ArrayList;
import java.util.List;

public class CrawUtils {

    public static List<String> handleAndSymbol(List<String> urls) {
        List<String> temp = new ArrayList<>();
        for (String s : urls) {
            if (s.length() < 2) continue;
            temp.add(s.replace("&amp;", "&"));
        }
        return temp;
    }

    public static String handleAndSymbol(String url) {
        if (url.length() < 2) return null;

        return url.replace("&amp;", "&");
    }

    public static JSONObject getAjaxResult(String path) {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
//            HttpGet get = new HttpGet("http://api.ip.data5u.com/dynamic/get.html?order=11900e890f534a5e7de582f6e041a269&json=1&sep=3");
            HttpGet get = new HttpGet(path);
            get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            get.setHeader("Accept","*/*");
            get.setHeader("Accept-Encoding","gzip, deflate, br");
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return JSON.parseObject(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
