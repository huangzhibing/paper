package com.hqu.modules.papercount.service;

import com.hqu.modules.papercount.entity.PaperCount;
import com.hqu.modules.papercount.mapper.PaperCountMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class PaperCountService extends CrudService<PaperCountMapper, PaperCount> {
    @Autowired
    PaperCountMapper paperCountMapper;

    public PaperCount get(String id) {
        return super.get(id);
    }

    public List<PaperCount> findList(PaperCount paperCount) {
        return super.findList(paperCount);
    }

    public Page<PaperCount> findPage(Page<PaperCount> page, PaperCount paperCount) {
        return super.findPage(page, paperCount);
    }

    @Transactional(readOnly = false)
    public void save(PaperCount paperCount) {
        super.save(paperCount);
    }

    @Transactional(readOnly = false)
    public void delete(PaperCount paperCount) {
        super.delete(paperCount);
    }
    public int selectOneType_Paper(String gxdm,String lwlxdm){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("gxdm",gxdm);
        objectMap.put("lwlxdm",lwlxdm);
        return paperCountMapper.selectOneType_Paper(objectMap);
    }
    public int selectOneTypePaper(String gxdm,String lwlxdm,String beginDate,String endDate){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("gxdm",gxdm);
        objectMap.put("lwlxdm",lwlxdm);
        objectMap.put("beginDate",beginDate);
        objectMap.put("endDate",endDate);
        return paperCountMapper.selectOneTypePaper(objectMap);
    }

    public List<PaperCount> getScName(PaperCount paperCount){
        return paperCountMapper.getScName(paperCount);
    }
}
