/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.expertmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hqu.modules.customermanage.expertmanagement.mapper.ExpertMapper;
import com.jeeplus.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;

/**
 * 专家信息表Service
 * @author 黄志兵
 * @version 2018-08-28
 */
@Service
@Transactional(readOnly = true)
public class ExpertService extends CrudService<ExpertMapper, Expert> {
	@Autowired
	ExpertMapper expertMapper;

	public Expert get(String id) {
		return super.get(id);
	}
	
	public List<Expert> findList(Expert expert) {
		return super.findList(expert);
	}
	
	public Page<Expert> findPage(Page<Expert> page, Expert expert) {
		return super.findPage(page, expert);
	}
	
	@Transactional(readOnly = false)
	public void save(Expert expert) {
		super.save(expert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expert expert) {
		super.delete(expert);
	}
	public int selectOneType_Expert(String dwdm,String zjlxdm){
		Map<String,Object> objectMap = new HashMap<>();
		objectMap.put("dwdm",dwdm);
		objectMap.put("zjlxdm",zjlxdm);
		return expertMapper.selectOneType_Expert(objectMap);
	}
	public int selectOneTypeExpert(String dwdm,String zjlxdm,String beginDate,String endDate,String zjztdm,String xbdm){
		Map<String,Object> objectMap = new HashMap<>();
		objectMap.put("dwdm",dwdm);
		objectMap.put("zjlxdm",zjlxdm);
		objectMap.put("beginDate",beginDate);
		objectMap.put("endDate",endDate);
		objectMap.put("zjztdm",zjztdm);
		objectMap.put("xbdm",xbdm);
		return expertMapper.selectOneTypeExpert(objectMap);
	}

	public int getExpertByDate(Map<String,Object> map){
		int num = expertMapper.getExpertByDate(map);
		return num;
	}

	@Transactional
	public void saveBankInfo(Expert expert){
		 if(StringUtils.isBlank(expert.getYhkh())){
		 	expertMapper.insertBankInfo(expert);
		 }else {
		 	expertMapper.updateBankInfo(expert);
		 }
	}
}