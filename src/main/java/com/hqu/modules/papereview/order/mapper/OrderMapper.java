/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.papereview.order.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.papereview.order.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单MAPPER接口
 * @author hzm
 * @version 2018-09-01
 */
@MyBatisMapper
public interface OrderMapper extends BaseMapper<Order> {

	public int getOrderByDate(Map<String,Object> map);
}