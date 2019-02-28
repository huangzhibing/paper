/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.papergrade.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.papergrade.entity.PaperGrade;

import java.util.Map;

/**
 * 论文等级统计MAPPER接口
 * @author zll
 * @version 2018-10-12
 */
@MyBatisMapper
public interface PaperGradeMapper extends BaseMapper<PaperGrade> {
    int getNum(Map<String, Object> objectMap);
}