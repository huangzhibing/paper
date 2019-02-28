/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.feedback.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.feedback.entity.Feedback;
import com.hqu.modules.basedata.feedback.mapper.FeedbackMapper;

import javax.xml.ws.Action;

/**
 * 反馈表Service
 * @author wy
 * @version 2018-09-05
 */
@Service
@Transactional(readOnly = true)
public class FeedbackService extends CrudService<FeedbackMapper, Feedback> {
	@Autowired
	FeedbackMapper feedbackMapper;

	public Feedback get(String id) {
		return super.get(id);
	}

	public List<Feedback> findList(Feedback feedback) {
		return super.findList(feedback);
	}

	public Page<Feedback> findPage(Page<Feedback> page, Feedback feedback) {
		return super.findPage(page, feedback);
	}

	@Transactional(readOnly = false)
	public void save(Feedback feedback) {
		super.save(feedback);
	}

	@Transactional(readOnly = false)
	public void delete(Feedback feedback) {
		super.delete(feedback);
	}

	@Transactional(readOnly = false)

	public String findmax() {
		return feedbackMapper.findmax();
	}
	public int getNumByDate(String beginDate, String endDate, String tableName, String filedName1,String data){
		Map<String,Object> objectMap = new HashMap<>();
		objectMap.put("tableName",tableName);
		objectMap.put("filedName1",filedName1);
		objectMap.put("data",data);
		objectMap.put("beginDate","'"+beginDate+"'");
		objectMap.put("endDate","'"+endDate+"'");

		int number = feedbackMapper.getNum(objectMap);
		return number;
	}

}