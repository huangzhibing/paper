/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.universitypaymentmanager.receives.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.universitypaymentmanager.receives.entity.Receives;
import org.apache.ibatis.annotations.Param;

/**
 * 订单管理MAPPER接口
 * @author ylf
 * @version 2018-10-04
 */
@MyBatisMapper
public interface ReceivesMapper extends BaseMapper<Receives> {
	void makeSure(@Param("id")String id);
}