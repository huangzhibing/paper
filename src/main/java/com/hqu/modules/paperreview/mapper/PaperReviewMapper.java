/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.paperreview.mapper;

import java.util.Map;

import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.paperreview.entity.PaperReview;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;

/**
 * 订单MAPPER接口
 * @author hzm
 * @version 2018-09-01
 */
@MyBatisMapper
public interface PaperReviewMapper extends BaseMapper<PaperReview> {
	public int review(PaperReview paperReview);
	
	public int addReviewTimes(String yhzh);
	
	public int addMoney(Map<String, Object> map);
	/**
	 * 评审状态改为null
	 * @return
	 */
	public int changePSZT(String yhzh);
	
}