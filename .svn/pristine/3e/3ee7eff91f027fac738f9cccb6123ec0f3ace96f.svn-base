/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.university.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 高校代码表Entity
 * @author wdz
 * @version 2018-12-17
 */
public class University extends DataEntity<University> {
	
	private static final long serialVersionUID = 1L;
	private String gxdm;		// 高校代码
	private String gxmc;		// 高校名称
	private Double gxwd;		// 高校纬度
	private Double gxjd;		// 高校经度
	private String whetheris985;		// 是否是985
	private String whetheris211;		// 是否是211
	
	public University() {
		super();
	}

	public University(String id){
		super(id);
	}

	@ExcelField(title="高校代码", align=2, sort=7)
	public String getGxdm() {
		return gxdm;
	}

	public void setGxdm(String gxdm) {
		this.gxdm = gxdm;
	}
	
	@ExcelField(title="高校名称", align=2, sort=8)
	public String getGxmc() {
		return gxmc;
	}

	public void setGxmc(String gxmc) {
		this.gxmc = gxmc;
	}
	
	@NotNull(message="高校纬度不能为空")
	@ExcelField(title="高校纬度", align=2, sort=9)
	public Double getGxwd() {
		return gxwd;
	}

	public void setGxwd(Double gxwd) {
		this.gxwd = gxwd;
	}
	
	@NotNull(message="高校经度不能为空")
	@ExcelField(title="高校经度", align=2, sort=10)
	public Double getGxjd() {
		return gxjd;
	}

	public void setGxjd(Double gxjd) {
		this.gxjd = gxjd;
	}
	
	@ExcelField(title="是否是985", align=2, sort=11)
	public String getWhetheris985() {
		return whetheris985;
	}

	public void setWhetheris985(String whetheris985) {
		this.whetheris985 = whetheris985;
	}
	
	@ExcelField(title="是否是211", align=2, sort=12)
	public String getWhetheris211() {
		return whetheris211;
	}

	public void setWhetheris211(String whetheris211) {
		this.whetheris211 = whetheris211;
	}
	
}