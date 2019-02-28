package com.hqu.modules.craw.runner;

import com.alibaba.fastjson.JSON;
import com.hqu.modules.common.util.RedisUtil;
import com.hqu.modules.craw.core.Core;
import com.hqu.modules.craw.core.SimpleCore;
import com.hqu.modules.craw.core.entity.*;
import com.hqu.modules.craw.pipeline.RedisPipeline;
import com.hqu.modules.craw.task.entity.Task;
import org.apache.commons.codec.binary.Base64;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

public class TaskRunner implements PageProcessor {

    private Core core;

    private Task task;


    private Site site = Site.me().setCycleRetryTimes(3).setSleepTime(100)
//            .addHeader("Proxy-Authorization", "Basic " + Base64.encodeBase64String("ZTLH1612388609143327:hsF9896gZ3CsMjGs".getBytes()))
            .setTimeOut(10000);


    private static final String SEP = ":";


    @Override
    public void process(Page page) {
        //爬取可用url
        page.addTargetRequests(core.getTargetUrl(task,page));
        //获取数据
        core.getTargetData(task,page);
        RedisUtil.set(CrawStatic.TASK+SEP+task.getId(), JSON.toJSON(task));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public TaskRunner(Core core, Task task) {
        this.core = core;
        this.task = task;
    }

    public static void main(String[] args) {
        Core core = new SimpleCore();
        Task task = new Task();
        task.setEnableAjax(false);
        task.setUrl("http://www.cs.zju.edu.cn/chinese/redir.php?catalog_id=101553");
        TargetURL targetURL = new TargetURL();
        targetURL.setMetaType(MetaType.REGEX);
        targetURL.setValue("           <a\\b[^>]+\\bhref=\"([^\"]*)\"[^>]*>[\\s\\S]*?</a>");
        List<TargetURL> targetURLList = new ArrayList<>();
        targetURLList.add(targetURL);
        task.setTargetURLList(new ArrayList<>(targetURLList));
        TargetData targetData1 = new TargetData();
        targetData1.setName("name");
        targetData1.setMetaType(MetaType.XPATH);
        targetData1.setValue("//*[@id='Content']/div/div/div[2]/div[1]/div/div[2]/div[1]/div[1]/span/text()");
        List<TargetData> targetDataList = new ArrayList<>();
        targetDataList.add(targetData1);
        task.setTargetDataList(targetDataList);
        Spider.create(new TaskRunner(core,task)).addUrl(task.getUrl()).addPipeline(new RedisPipeline(task)).thread(5).run();
    }
}
