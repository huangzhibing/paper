/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.organizationmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.organizationmanagement.entity.OrganizationManagement;
import com.hqu.modules.basedata.organizationmanagement.mapper.OrganizationManagementMapper;

/**
 * 单位代码表Service
 * @author nzx
 * @version 2018-09-09
 */
@Service
@Transactional(readOnly = true)
public class OrganizationManagementService extends CrudService<OrganizationManagementMapper, OrganizationManagement> {

	public OrganizationManagement get(String id) {
		return super.get(id);
	}
	
	public List<OrganizationManagement> findList(OrganizationManagement organizationManagement) {
		return super.findList(organizationManagement);
	}
	
	public Page<OrganizationManagement> findPage(Page<OrganizationManagement> page, OrganizationManagement organizationManagement) {
		return super.findPage(page, organizationManagement);
	}
	
	@Transactional(readOnly = false)
	public void save(OrganizationManagement organizationManagement) {
		super.save(organizationManagement);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrganizationManagement organizationManagement) {
		super.delete(organizationManagement);
	}

	public 	List<Map<String,String>> orgMap() {
		return mapper.orgMap();
	}

}