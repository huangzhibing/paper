/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.department.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.department.entity.DepartmentManage;
import com.hqu.modules.basedata.department.mapper.DepartmentManageMapper;

/**
 * 学院代码Service
 * @author zw
 * @version 2018-12-11
 */
@Service
@Transactional(readOnly = true)
public class DepartmentManageService extends CrudService<DepartmentManageMapper, DepartmentManage> {

	public DepartmentManage get(String id) {
		return super.get(id);
	}
	
	public List<DepartmentManage> findList(DepartmentManage departmentManage) {
		return super.findList(departmentManage);
	}
	
	public Page<DepartmentManage> findPage(Page<DepartmentManage> page, DepartmentManage departmentManage) {
		return super.findPage(page, departmentManage);
	}
	
	@Transactional(readOnly = false)
	public void save(DepartmentManage departmentManage) {
		super.save(departmentManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(DepartmentManage departmentManage) {
		super.delete(departmentManage);
	}
	
}