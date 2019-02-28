/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.review.mapper;

import com.hqu.modules.papermanage.entity.Paper;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;


/**
 * 评审记录MAPPER接口
 * @author nzx
 * @version 2018-09-07
 */
@MyBatisMapper
public interface ReviewMapper extends BaseMapper<Paper> {
	
}