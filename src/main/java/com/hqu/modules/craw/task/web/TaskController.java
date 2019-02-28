package com.hqu.modules.craw.task.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqu.modules.craw.core.Core;
import com.hqu.modules.craw.core.MultiStepCore;
import com.hqu.modules.craw.core.SimpleCore;
import com.hqu.modules.craw.core.entity.TargetData;
import com.hqu.modules.craw.core.entity.TargetURL;
import com.hqu.modules.craw.core.entity.TaskType;
import com.hqu.modules.craw.core.proxy.DynamicProxyProvider;
import com.hqu.modules.craw.info.entity.Info;
import com.hqu.modules.craw.info.service.CrawInfoService;
import com.hqu.modules.craw.pipeline.RedisPipeline;
import com.hqu.modules.craw.runner.TaskRunner;
import com.hqu.modules.craw.task.entity.Task;
import com.hqu.modules.craw.task.service.CrawTaskService;
import com.hqu.modules.papermanage.entity.Paper;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.common.utils.excel.ExportExcel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/task/crawTask")
public class TaskController {
    @Autowired
    private CrawTaskService taskService;
    @Autowired
    private CrawInfoService infoService;

    @RequestMapping(value = {"list", ""})
    public String list(Model model) {
        return "craw/task/crawTaskList";
    }
    @RequestMapping("taskData")
    @ResponseBody
    public String taskData() {
        return JSON.toJSONString(taskService.getAllTask());
    }

    @RequestMapping("add")
    @ResponseBody
    public String addTask(@RequestBody Task task) {
        task.setTotalUrlSize(0);
        task.setCompleteSize(0);
        String id = taskService.save(task);
        JSONObject response = new JSONObject();
        if(StringUtils.isEmpty(id)) {
            response.put("success",false);
        } else {
            response.put("success",true);
            response.put("id",id);
        }
        return response.toJSONString();
    }
    @RequestMapping("view")
    @ResponseBody
    public String viewInfo(String id) {
        List<Info> infoList = infoService.getInfoByTaskId(id);
        JSONObject response = new JSONObject();
        if(StringUtils.isEmpty(infoList)) {
            response.put("success",false);
        } else {
            response.put("success",true);
            response.put("data",infoList);
        }
        return response.toJSONString();
    }

    @RequestMapping("update")
    @ResponseBody
    public String updateTask(@RequestBody Task task) {
        String id = taskService.save(task);
        JSONObject response = new JSONObject();
        if(StringUtils.isEmpty(id)) {
            response.put("success",false);
        } else {
            response.put("success",true);
            response.put("id",id);
        }
        return response.toJSONString();
    }
    @RequestMapping("del")
    @ResponseBody
    public String delTask(String id) {
        Task task = taskService.get(id);
        JSONObject response = new JSONObject();
        taskService.del(id);
        if(StringUtils.isEmpty(task)) {
            response.put("success",false);
            response.put("msg","该任务不存在");
        } else {
            response.put("success",true);
        }
        return response.toJSONString();
    }
    @RequestMapping("run")
    @ResponseBody
    public String run(String id) {
        Task task = taskService.get(id);
        task.setCompleteSize(0);
        task.setTotalUrlSize(0);
        task.setFailSize(0);
        taskService.save(task);
        infoService.deleteAll(task.getId());
        Core core;
        if(TaskType.TYPE_SIMPLE.equals(task.getType())) {
            core = new SimpleCore();
        } else if(TaskType.TYPE_MULTISTEP.equals(task.getType())) {
            core = new MultiStepCore();
        } else {
            core = new SimpleCore();
        }
        TaskRunner tr = new TaskRunner(core,task);
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//        httpClientDownloader.setProxyProvider(new DynamicProxyProvider());
        httpClientDownloader.setProxyProvider(new ProxyProvider() {
            @Override
            public void returnProxy(Proxy proxy, Page page, us.codecraft.webmagic.Task task) {

            }

            @Override
            public Proxy getProxy(us.codecraft.webmagic.Task task) {
                return new Proxy("dyn.horocn.com",50000);
            }
        });
        Spider.create(tr).addUrl(task.getUrl()).addPipeline(new RedisPipeline(task))
//                .setDownloader(httpClientDownloader)
//                .setScheduler(new RedisScheduler(((JedisPool)SpringContextHolder.getBean("crawJedisPool"))))
                .thread(5).start();
        JSONObject response = new JSONObject();
        if(StringUtils.isEmpty(task)) {
            response.put("success",false);
            response.put("msg","该任务不存在");
        } else {
            response.put("success",true);
        }
        return response.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "export")
    public AjaxJson exportFile(String taskId, HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        try {
            String fileName = "爬取结果"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Task task = taskService.get(taskId);
            List<String> headers = new ArrayList<>();
            headers.add("url");
            for(TargetData targetData : task.getTargetDataList()) {
                headers.add(targetData.getName());
            }

            List<Info> infoList = infoService.getInfoByTaskId(taskId);
            List<Map<String,Object>> dataList = new ArrayList<>();
            Map<String,Object> map;
            for(Info o : infoList) {
                map = new HashMap<>();
                map.put("url",o.getUrl());
//                dataList.add(o.get("url"));
                for(TargetData targetData : task.getTargetDataList()) {
//                    dataList.add(((Map<String,Object>)o.get("object")).get(targetData.getName()));
                    map.put(targetData.getName(),(o.getObject().get(targetData.getName())));
                }
                dataList.add(map);
            }
            new ExportExcel("爬取结果",headers).setDataListMap(dataList).write(response, fileName).dispose();
            j.setSuccess(true);
            j.setMsg("导出成功！");
            return j;
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("导出论文管理记录失败！失败信息："+e.getMessage());
        }
        return j;
    }
}
