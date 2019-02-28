package com.hqu.modules.papermanage.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jeeplus.core.persistence.annotation.MyBatisMapper;
@MyBatisMapper
public interface PaperSelfMapper {
	public int updatePaperPath(Map<String, Object> map);
}
