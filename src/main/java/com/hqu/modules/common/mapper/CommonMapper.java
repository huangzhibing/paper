package com.hqu.modules.common.mapper;

import com.jeeplus.core.persistence.annotation.MyBatisMapper;

import java.util.Map;

/**
 * 公共MAPPER接口
 * @author hzb
 * @version 2018-08-29
 */

@MyBatisMapper
public interface CommonMapper {

    String checkOnly(Map<String,Object> statement);

    String checkOnlyt(Map<String,Object> statement);

    int getNumByDate(Map<String,Object> objectMap);
}
