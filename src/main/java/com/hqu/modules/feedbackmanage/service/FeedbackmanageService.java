/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.feedbackmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.feedbackmanage.entity.Feedbackmanage;
import com.hqu.modules.feedbackmanage.mapper.FeedbackmanageMapper;

/**
 * 反馈Service
 * @author wdz
 * @version 2018-09-09
 */
@Service
@Transactional(readOnly = true)
public class FeedbackmanageService extends CrudService<FeedbackmanageMapper, Feedbackmanage> {

	public Feedbackmanage get(String id) {
		return super.get(id);
	}
	
	public List<Feedbackmanage> findList(Feedbackmanage feedbackmanage) {
		return super.findList(feedbackmanage);
	}
	
	public Page<Feedbackmanage> findPage(Page<Feedbackmanage> page, Feedbackmanage feedbackmanage) {
		return super.findPage(page, feedbackmanage);
	}
	
	@Transactional(readOnly = false)
	public void save(Feedbackmanage feedbackmanage) {
		super.save(feedbackmanage);
	}
	
	@Transactional(readOnly = false)
	public void delete(Feedbackmanage feedbackmanage) {
		super.delete(feedbackmanage);
	}
	
}