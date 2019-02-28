package com.hqu.modules.craw.core.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicProxyProvider implements ProxyProvider {

    protected Logger logger = LoggerFactory.getLogger(DynamicProxyProvider.class);

    private List<Proxy> proxies = new ArrayList<>();
    private final AtomicInteger pointer = new AtomicInteger(0);

    private Long lastUpdateTime;

    public DynamicProxyProvider() {
        updateProxy();
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {

    }

    private void updateProxy() {
        logger.debug("更新代理池");
        CloseableHttpClient client = HttpClients.createDefault();
        try {
//            HttpGet get = new HttpGet("http://api.ip.data5u.com/dynamic/get.html?order=11900e890f534a5e7de582f6e041a269&json=1&sep=3");
            HttpGet get = new HttpGet("https://proxy.horocn.com/api/proxies?order_id=LUT21612380012093435&num=5&format=json&line_separator=win");
            CloseableHttpResponse response = client.execute(get);
            lastUpdateTime = System.currentTimeMillis();
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            logger.debug(result);
//            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray array = JSON.parseArray(result);
            proxies = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                Proxy proxy = new Proxy(array.getJSONObject(i).getString("host"),array.getJSONObject(i).getInteger("port"));
                proxies.add(proxy);
            }

            logger.debug("更新代理池完成:" + array.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private int incrForLoop() {
        int p = this.pointer.incrementAndGet();
        int size = this.proxies.size();
        if (p < size) {
            return p;
        } else {
            while(!this.pointer.compareAndSet(p, p % size)) {
                p = this.pointer.get();
            }

            return p % size;
        }
    }

    @Override
    public Proxy getProxy(Task task) {
        System.out.println(System.currentTimeMillis());
        if(System.currentTimeMillis() - this.lastUpdateTime >= 3 * 60 * 900) {
            this.updateProxy();
        }
        return this.proxies.get(this.incrForLoop());
    }
}
