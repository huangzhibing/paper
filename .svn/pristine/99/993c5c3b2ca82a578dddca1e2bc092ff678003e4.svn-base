/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.paperreceive.daishenpaper.service;

import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.paperreceive.daishenpaper.mapper.DaishenPaperMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评审记录Service
 * @author hzb
 * @version 2018-09-07
 */
@Service
@Transactional(readOnly = true)
public class DaishenPaperService extends CrudService<DaishenPaperMapper, Paper> {

	public Paper get(String id) {
		return super.get(id);
	}

	public List<Paper> findList(Paper paper) {
		return super.findList(paper);
	}

	public Page<Paper> findPage(Page<Paper> page, Paper paper) {	return super.findPage(page, paper); }

	@Transactional(readOnly = false)
	public void save(Paper paper) {
		super.save(paper);
	}

	@Transactional(readOnly = false)
	public void delete(Paper paper) {
		super.delete(paper);
	}

	
}