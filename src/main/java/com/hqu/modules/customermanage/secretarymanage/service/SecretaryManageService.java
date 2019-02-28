/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.secretarymanage.service;

import java.util.List;

import com.hqu.modules.customermanage.secretarymanage.entity.SecretaryManage;
import com.hqu.modules.customermanage.secretarymanage.mapper.SecretaryManageMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 学院秘书信息管理Service
 * @author ljc
 * @version 2018-12-13
 */
@Service
@Transactional(readOnly = true)
public class SecretaryManageService extends CrudService<SecretaryManageMapper, SecretaryManage> {

	public SecretaryManage get(String id) {
		return super.get(id);
	}
	
	public List<SecretaryManage> findList(SecretaryManage secretaryManage) {
		return super.findList(secretaryManage);
	}
	
	public Page<SecretaryManage> findPage(Page<SecretaryManage> page, SecretaryManage secretaryManage) {
		return super.findPage(page, secretaryManage);
	}
	
	@Transactional(readOnly = false)
	public void save(SecretaryManage secretaryManage) {
		super.save(secretaryManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecretaryManage secretaryManage) {
		super.delete(secretaryManage);
	}

	public String getRoleIdByUserId(String userid){
		return mapper.getRoleIdByUserId(userid);
	}
}