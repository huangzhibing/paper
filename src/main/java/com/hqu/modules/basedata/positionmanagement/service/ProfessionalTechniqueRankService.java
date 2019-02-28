/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.positionmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.basedata.positionmanagement.entity.ProfessionalTechniqueRank;
import com.hqu.modules.basedata.positionmanagement.mapper.ProfessionalTechniqueRankMapper;

/**
 * 专业技术级别Service
 * @author zll
 * @version 2018-09-25
 */
@Service
@Transactional(readOnly = true)
public class ProfessionalTechniqueRankService extends CrudService<ProfessionalTechniqueRankMapper, ProfessionalTechniqueRank> {

	public ProfessionalTechniqueRank get(String id) {
		return super.get(id);
	}
	
	public List<ProfessionalTechniqueRank> findList(ProfessionalTechniqueRank professionalTechniqueRank) {
		return super.findList(professionalTechniqueRank);
	}
	
	public Page<ProfessionalTechniqueRank> findPage(Page<ProfessionalTechniqueRank> page, ProfessionalTechniqueRank professionalTechniqueRank) {
		return super.findPage(page, professionalTechniqueRank);
	}
	
	@Transactional(readOnly = false)
	public void save(ProfessionalTechniqueRank professionalTechniqueRank) {
		super.save(professionalTechniqueRank);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProfessionalTechniqueRank professionalTechniqueRank) {
		super.delete(professionalTechniqueRank);
	}
	
}