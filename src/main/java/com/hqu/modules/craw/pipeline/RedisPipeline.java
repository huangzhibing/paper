package com.hqu.modules.craw.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqu.modules.craw.info.entity.Info;
import com.hqu.modules.craw.info.service.CrawInfoService;
import com.hqu.modules.craw.task.service.CrawTaskService;
import com.jeeplus.common.utils.SpringContextHolder;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class RedisPipeline implements Pipeline {
    private com.hqu.modules.craw.task.entity.Task task;

    private CrawInfoService service = SpringContextHolder.getBean(CrawInfoService.class);

    private CrawTaskService taskService = SpringContextHolder.getBean(CrawTaskService.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
//        JSONObject jsonObject = new JSONObject(resultItems.getAll());
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultItems.getAll()));
        Info info = new Info(jsonObject,this.getTask().getId(),resultItems.getRequest().getUrl());
        service.save(info);
        this.getTask().setCompleteSize(this.getTask().getCompleteSize() + 1);
        taskService.save(this.getTask());
    }

    public com.hqu.modules.craw.task.entity.Task getTask() {
        return task;
    }

    public void setTask(com.hqu.modules.craw.task.entity.Task task) {
        this.task = task;
    }

    public RedisPipeline(com.hqu.modules.craw.task.entity.Task task) {
        this.task = task;
    }
}
