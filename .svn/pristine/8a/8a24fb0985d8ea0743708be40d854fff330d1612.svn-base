package com.hqu.modules.craw.info.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqu.modules.common.util.RedisUtil;
import com.hqu.modules.craw.info.entity.Info;
import com.hqu.modules.craw.task.entity.Task;
import net.oschina.j2cache.CacheObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CrawInfoService {
    private static final String INFO = "CRAW_INFO";
    private static final String SEP = ":";
    public String save(Info info) {
//            RedisUtil.set(info.getTaskId()+SEP+info.getId(), JSON.toJSON(info));
            RedisUtil.addList(info.getTaskId(),info);
            return info.getId();
    }

    public List<Info> getInfoByTaskId(String taskId) {
//        Collection<String> keys = RedisUtil.getAll(taskId + SEP + INFO);
//        Map<String, CacheObject> keys = RedisUtil.getAll(taskId);
//        List<JSONObject> infos = new ArrayList<>();
//        for(CacheObject o : keys.values()) {
//            if(o != null && o.getValue() != null) {
//                infos.add((JSONObject) o.getValue());
//            }
//        }
        return  RedisUtil.getList(taskId);
    }

/*    public Info get(String taskId,String uuid)  {
        Object task = RedisUtil.get(taskId,uuid);
        if(task == null) {
            return null;
        }
        return JSON.parseObject(task.toString(),Info.class);
    }*/

/*    public void del(String taskId,String uuid) {
        RedisUtil.li(taskId,uuid);
    }*/

    public void deleteAll(String taskId) {
//        RedisUtil.setList(taskId,new ArrayList<>());
        RedisUtil.del(taskId);
    }
}
