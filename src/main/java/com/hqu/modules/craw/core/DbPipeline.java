package com.hqu.modules.craw.core;/*
package com.hqu.modules.craw.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqu.modules.craw.info.entity.CrawInfo;
import com.hqu.modules.craw.info.mapper.CrawInfoMapper;
import com.hqu.modules.craw.info.service.CrawInfoService;
import com.hqu.modules.craw.type.entity.CrawTaskType;
import com.hqu.modules.craw.type.service.CrawTaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

@Component
public class DbPipeline implements Pipeline {
    @Autowired
    private CrawInfoService service;
    @Autowired
    private CrawTaskTypeService crawTaskTypeService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONObject jsonObject = new JSONObject(resultItems.getAll());
        CrawInfo crawInfo = JSON.toJavaObject(jsonObject,CrawInfo.class);
        if(crawInfo.getName() != null && crawInfo.getName() != "") {
            crawInfo.setUrl(resultItems.getRequest().getUrl());
            CrawTaskType type = crawTaskTypeService.get((String)resultItems.get("typeId"));
            crawInfo.setCrawType(type);
            service.save(crawInfo);
        }
    }
}
*/
