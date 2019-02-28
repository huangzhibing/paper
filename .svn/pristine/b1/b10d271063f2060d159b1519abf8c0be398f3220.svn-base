/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.papercount.mapper;

import com.hqu.modules.papercount.entity.PaperCount;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;

import java.util.List;
import java.util.Map;

@MyBatisMapper
public interface PaperCountMapper extends BaseMapper<PaperCount> {
    public List<PaperCount> getScName(PaperCount paperCount);

    public int selectOneType_Paper(Map<String,Object> objectMap);
    public int selectOneTypePaper(Map<String,Object> objectMap);
}