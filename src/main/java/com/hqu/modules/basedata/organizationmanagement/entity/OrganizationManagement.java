/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.organizationmanagement.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

/**
 * 单位代码表Entity
 * @author nzx
 * @version 2018-09-09
 */
public class OrganizationManagement extends DataEntity<OrganizationManagement> {
	
	private static final long serialVersionUID = 1L;
	private String dwdm;		// 单位代码
	private String dwmc;		// 单位名称
	private Double dwjd;
	private Double dwwd;
	
	public OrganizationManagement() {
		super();
	}

	public OrganizationManagement(String id){
		super(id);
	}

	@Length(min=1, max=8, message="单位代码长度必须介于 1 到 8 位之间")
	@ExcelField(title="单位代码", align=2, sort=6)
	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	
	@ExcelField(title="单位名称", align=2, sort=7)
	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public Double getDwjd() {
		return dwjd;
	}

	public void setDwjd(Double dwjd) {
		this.dwjd = dwjd;
	}

	public Double getDwwd() {
		return dwwd;
	}

	public void setDwwd(Double dwwd) {
		this.dwwd = dwwd;
	}
}