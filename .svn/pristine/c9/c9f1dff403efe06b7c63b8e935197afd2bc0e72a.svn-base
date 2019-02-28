/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.specialitymanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;
import com.hqu.modules.basedata.specialitymanage.mapper.SpecialityManageMapper;

/**
 * 该表用来存储专业信息Service
 * @author ylf
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly = true)
public class SpecialityManageService extends CrudService<SpecialityManageMapper, SpecialityManage> {

	public SpecialityManage get(String id) {
		return super.get(id);
	}
	
	public List<SpecialityManage> findList(SpecialityManage specialityManage) {
		return super.findList(specialityManage);
	}
	
	public Page<SpecialityManage> findPage(Page<SpecialityManage> page, SpecialityManage specialityManage) {
		return super.findPage(page, specialityManage);
	}
	
	@Transactional(readOnly = false)
	public void save(SpecialityManage specialityManage) {
		super.save(specialityManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(SpecialityManage specialityManage) {
		super.delete(specialityManage);
	}
	
}