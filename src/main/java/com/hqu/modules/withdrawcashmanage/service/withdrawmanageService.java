/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.withdrawcashmanage.service;

import com.hqu.modules.customermanage.ordermanage.entity.OrderManage;
import com.hqu.modules.customermanage.ordermanage.mapper.OrderManageMapper;
import com.hqu.modules.withdrawcashmanage.mapper.withdrawmanageMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 提现管理Service
 * @author nzx
 * @version 2018-10-01
 */
@Service
@Transactional(readOnly = true)
public class withdrawmanageService extends CrudService<withdrawmanageMapper, OrderManage> {

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