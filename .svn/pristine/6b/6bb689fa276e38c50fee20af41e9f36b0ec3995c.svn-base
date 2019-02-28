/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.studentmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.studentmanage.entity.StudentManage;
import com.hqu.modules.basedata.studentmanage.mapper.StudentManageMapper;

/**
 * 学生管理Service
 * @author ljc
 * @version 2018-09-10
 */
@Service
@Transactional(readOnly = true)
public class StudentManageService extends CrudService<StudentManageMapper, StudentManage> {

	public StudentManage get(String id) {
		return super.get(id);
	}
	
	public List<StudentManage> findList(StudentManage studentManage) {
		return super.findList(studentManage);
	}
	
	public Page<StudentManage> findPage(Page<StudentManage> page, StudentManage studentManage) {
		return super.findPage(page, studentManage);
	}

	public String getMaxIdByMsdm(String para){
		return mapper.getMaxIdByMsdm(para);
	}

	@Transactional(readOnly = false)
	public void save(StudentManage studentManage) {
		super.save(studentManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentManage studentManage) {
		super.delete(studentManage);
	}
	
}