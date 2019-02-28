/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.ordermanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.customermanage.ordermanage.entity.OrderManage;
import com.hqu.modules.customermanage.ordermanage.mapper.OrderManageMapper;

/**
 * 订单管理Service
 * @author hzb
 * @version 2018-09-02
 */
@Service
@Transactional(readOnly = true)
public class OrderManageService extends CrudService<OrderManageMapper, OrderManage> {

	public OrderManage get(String id) {
		return super.get(id);
	}
	
	public List<OrderManage> findList(OrderManage orderManage) {
		return super.findList(orderManage);
	}
	
	public Page<OrderManage> findPage(Page<OrderManage> page, OrderManage orderManage) {
		return super.findPage(page, orderManage);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderManage orderManage) {
		super.save(orderManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderManage orderManage) {
		super.delete(orderManage);
	}
	
}