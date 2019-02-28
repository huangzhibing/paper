package com.hqu.modules.craw.task.service;

import com.alibaba.fastjson.JSON;
import com.hqu.modules.common.util.RedisUtil;
import com.hqu.modules.craw.task.entity.Task;
import net.oschina.j2cache.CacheObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrawTaskService {
    private static final String TASK = "CRAW_TASK";
    private static final String SEP = ":";
    public String save(Task task) {
            RedisUtil.set(TASK+SEP+task.getId(), task);
            return task.getId();
    }

    public List<Task> getAllTask() {
//        Map<String, CacheObject> keys = RedisUtil.(TASK);
        Set<String> keys = RedisUtil.keys(TASK+"*");
        List<Task> tasks = new ArrayList<>();
        Task task;
        for(String key : keys) {
            task = get(key);
            if(task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public Task get(String uuid)  {
        if(!uuid.contains(TASK)){
            uuid = TASK+SEP+uuid;
        }
        Task task = RedisUtil.getObj(uuid,Task.class);
        if(task == null) {
            return null;
        }
        return task;
    }

    public void del(String uuid) {
        RedisUtil.del(TASK+SEP+uuid);
    }
}
