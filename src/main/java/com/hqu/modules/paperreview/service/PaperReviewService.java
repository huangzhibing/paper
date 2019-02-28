/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.paperreview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;


import com.hqu.modules.paperreview.entity.PaperReview;
import com.hqu.modules.paperreview.mapper.PaperReviewMapper;

/**
 * 订单Service
 * @author hzm
 * @version 2018-09-01
 */
@Service
@Transactional(readOnly = true)
public class PaperReviewService extends CrudService<PaperReviewMapper, PaperReview> {

	public PaperReview get(String id) {
		return super.get(id);
	}
	
	public List<PaperReview> findList(PaperReview order) {
		return super.findList(order);
	}
	
	public Page<PaperReview> findPage(Page<PaperReview> page, PaperReview order) {
		return super.findPage(page, order);
	}
	
	@Transactional(readOnly = false)
	public void save(PaperReview order) {
		super.save(order);
	}
	
	@Transactional(readOnly = false)
	public void delete(PaperReview order) {
		super.delete(order);
	}
	@Transactional(readOnly = false)
	public int review(PaperReview paperReview) {
		return mapper.review(paperReview);
	}
	@Transactional(readOnly = false)
	public int addReviewTimes(String yhzh) {
		return mapper.addReviewTimes(yhzh);
	}
	@Transactional(readOnly = false)
	public int addMoney(Map<String, Object> map) {
		return mapper.addMoney(map);
	}
	@Transactional(readOnly = false)
	public int changePSZT(String yhzh) {
		return mapper.changePSZT(yhzh);
	}
}