/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.specialitymanage.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 该表用来存储专业信息Entity
 * @author ylf
 * @version 2018-08-29
 */
public class SpecialityManage extends DataEntity<SpecialityManage> {
	
	private static final long serialVersionUID = 1L;
	private String zydm;		// 专业代码
	private String zymc;		// 专业名称
	
	public SpecialityManage() {
		super();
	}

	public SpecialityManage(String id){
		super(id);
	}

	@Length(min=2, max=6, message="专业代码长度必须介于 2 和 6 之间")
	@ExcelField(title="专业代码", align=2, sort=7)
	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	
	@ExcelField(title="专业名称", align=2, sort=8)
	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
}