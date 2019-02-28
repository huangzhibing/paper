/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.positionmanagement.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 专业技术级别Entity
 * @author zll
 * @version 2018-09-25
 */
public class ProfessionalTechniqueRank extends DataEntity<ProfessionalTechniqueRank> {
	
	private static final long serialVersionUID = 1L;
	private String rankCode;		// 专业技术级别代码
	private String rankName;		// 专业技术级别名称
	private String rankTitle;		// 岗位等级
	private String rankSection;		// 国家专业技术岗位级别
	
	public ProfessionalTechniqueRank() {
		super();
	}

	public ProfessionalTechniqueRank(String id){
		super(id);
	}

	@Length(min=1, max=10, message="专业技术级别代码长度必须介于 1 和 10 之间")
	@ExcelField(title="专业技术级别代码", align=2, sort=7)
	public String getRankCode() {
		return rankCode;
	}

	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	
	@Length(min=1, max=20, message="专业技术级别名称长度必须介于 1 和 20 之间")
	@ExcelField(title="专业技术级别名称", align=2, sort=8)
	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	
	@ExcelField(title="岗位等级", dictType="T_RankTitle", align=2, sort=9)
	public String getRankTitle() {
		return rankTitle;
	}

	public void setRankTitle(String rankTitle) {
		this.rankTitle = rankTitle;
	}
	
	@ExcelField(title="国家专业技术岗位级别", dictType="T_RankSection", align=2, sort=10)
	public String getRankSection() {
		return rankSection;
	}

	public void setRankSection(String rankSection) {
		this.rankSection = rankSection;
	}
	
}