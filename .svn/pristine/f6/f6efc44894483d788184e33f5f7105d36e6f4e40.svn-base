/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.costc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.costc.entity.Costc;
import com.hqu.modules.basedata.costc.mapper.CostcMapper;

/**
 * 费用表Service
 * @author wy
 * @version 2018-10-04
 */
@Service
@Transactional(readOnly = true)
public class CostcService extends CrudService<CostcMapper, Costc> {

	public Costc get(String id) {
		return super.get(id);
	}
	
	public List<Costc> findList(Costc costC) {
		return super.findList(costC);
	}
	
	public Page<Costc> findPage(Page<Costc> page, Costc costC) {
		return super.findPage(page, costC);
	}
	
	@Transactional(readOnly = false)
	public void save(Costc costC) {
		super.save(costC);
	}
	
	@Transactional(readOnly = false)
	public void delete(Costc costC) {
		super.delete(costC);
	}
	
}