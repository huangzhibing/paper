package com.hqu.modules.craw.core;/*
package com.hqu.modules.craw.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqu.modules.craw.info.entity.CrawInfo;
import com.hqu.modules.craw.info.mapper.CrawInfoMapper;
import com.hqu.modules.craw.info.service.CrawInfoService;
import com.hqu.modules.craw.task.entity.CrawTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class CrawCore implements PageProcessor {


    @Resource
    private CrawInfoMapper mapper;

    private CrawTask crawTask;

    private Site site = Site.me().setRetryTimes(3).setCycleRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        if(crawTask.getType() == null || crawTask.getType().equals("0")) {
            List<String> html = page.getHtml().regex(crawTask.getTargetUrl()).all();
            List<String> urls = new ArrayList<>();
            for (String url : html) {
                url = crawTask.getUrlPrefix() + url;
                urls.add(url);
            }
            page.addTargetRequests(urls);
            if (crawTask.getCrawMethod().equalsIgnoreCase("xpath")) {
                for (String key : crawTask.getMap().keySet()) {
                    page.putField(key, page.getHtml().xpath(crawTask.getMap().get(key)));
                }
            } else if (crawTask.getCrawMethod().equalsIgnoreCase("regex")) {
                for (String key : crawTask.getMap().keySet()) {
                    page.putField(key, page.getHtml().regex(crawTask.getMap().get(key)));
                }
            }
            page.putField("typeId", crawTask.getCrawType().getId());
        } else if(crawTask.getType().equals("1")) {
            crawTask.setTargetUrl(HtmlUtils.htmlUnescape(crawTask.getTargetUrl()));
            String[] helpUrls = crawTask.getHelpUrl().split(";");
            String[] targetUrls = crawTask.getTargetUrl().split(";");
            String[] prefixs = crawTask.getUrlPrefix().split(";");
            String[] titles = crawTask.getTitle().split(";");
            //第一级
            String testTitle = page.getHtml().$("head > title").toString();
//            if(page.getHtml().$("head > title").toString().contains(titles[0])) {
            if(page.getUrl().toString().contains(helpUrls[0])) {
                List<String> html = page.getHtml().regex(targetUrls[0]).all();
                List<String> urls = new ArrayList<>();
                for (String url : html) {
                    url = prefixs[0] + url;
                    urls.add(url);
                }
                page.addTargetRequests(urls);
            } else if(page.getUrl().toString().contains("http://oldmypage.zju.edu.cn/dept-person-5")){
                List<String> html = page.getHtml().regex(targetUrls[1]).all();
                List<String> urls = new ArrayList<>();
                for (String url : html) {
                    url = prefixs[1] + url;
                    url = HtmlUtils.htmlUnescape(url);
                    if(page.getTargetRequests().contains(new Request(url))) {
                        continue;
                    }
                    urls.add(url);
                }
                page.addTargetRequests(urls);
                if(helpUrls.length == 2) {
                    List<String> htmls = page.getHtml().regex(targetUrls[2]).all();
                    List<String> urlList = new ArrayList<>();
                    for (String url : htmls) {
                        url = prefixs[2] + url;
                        if(page.getTargetRequests().contains(new Request(url))) {
                            continue;
                        }
                        urlList.add(url);
                    }
                    page.addTargetRequests(urlList);

                }
            } else if(page.getUrl().toString().contains("http://person.zju.edu.cn/")){
                for (String key : crawTask.getMap().keySet()) {
                    page.putField(key, page.getHtml().xpath(crawTask.getMap().get(key)));
                }
            }
//            page.addTargetRequests(urls);
//            if (crawTask.getCrawMethod().equalsIgnoreCase("xpath")) {
//                for (String key : crawTask.getMap().keySet()) {
//                    page.putField(key, page.getHtml().xpath(crawTask.getMap().get(key)));
//                }
//            } else if (crawTask.getCrawMethod().equalsIgnoreCase("regex")) {
//                for (String key : crawTask.getMap().keySet()) {
//                    page.putField(key, page.getHtml().regex(crawTask.getMap().get(key)));
//                }
//            }
//            page.putField("typeId", crawTask.getCrawType().getId());
        }
//        System.out.println(JSON.toJSONString(crawTask));
*/
/*        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }*//*

    }

    public CrawTask getCrawTask() {
        return crawTask;
    }

    public void setCrawTask(CrawTask crawTask) {
        this.crawTask = crawTask;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public CrawCore(CrawTask crawTask) {
        this.crawTask = crawTask;
    }
}
*/
