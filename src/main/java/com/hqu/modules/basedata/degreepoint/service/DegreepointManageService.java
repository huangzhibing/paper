/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.degreepoint.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.degreepoint.entity.DegreepointManage;
import com.hqu.modules.basedata.degreepoint.mapper.DegreepointManageMapper;

/**
 * 学位点代码表Service
 * @author 张卫
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly = true)
public class DegreepointManageService extends CrudService<DegreepointManageMapper, DegreepointManage> {

	public DegreepointManage get(String id) {
		return super.get(id);
	}
	
	public List<DegreepointManage> findList(DegreepointManage degreepointManage) {
		return super.findList(degreepointManage);
	}
	
	public Page<DegreepointManage> findPage(Page<DegreepointManage> page, DegreepointManage degreepointManage) {
		return super.findPage(page, degreepointManage);
	}
	
	@Transactional(readOnly = false)
	public void save(DegreepointManage degreepointManage) {
		super.save(degreepointManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(DegreepointManage degreepointManage) {
		super.delete(degreepointManage);
	}
	
}