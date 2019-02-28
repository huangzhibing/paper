/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.feedback_count.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.feedback_count.entity.Feedback_count;
import com.hqu.modules.feedback_count.mapper.Feedback_countMapper;

/**
 * 反馈统计Service
 * @author wdz
 * @version 2018-09-24
 */
@Service
@Transactional(readOnly = true)
public class Feedback_countService extends CrudService<Feedback_countMapper, Feedback_count> {

	public Feedback_count get(String id) {
		return super.get(id);
	}
	
	public List<Feedback_count> findList(Feedback_count feedback_count) {
		return super.findList(feedback_count);
	}
	
	public Page<Feedback_count> findPage(Page<Feedback_count> page, Feedback_count feedback_count) {
		return super.findPage(page, feedback_count);
	}
	
	@Transactional(readOnly = false)
	public void save(Feedback_count feedback_count) {
		super.save(feedback_count);
	}
	
	@Transactional(readOnly = false)
	public void delete(Feedback_count feedback_count) {
		super.delete(feedback_count);
	}

	
}