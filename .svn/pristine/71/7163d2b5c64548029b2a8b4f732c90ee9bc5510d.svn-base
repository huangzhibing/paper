/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.department.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 学院代码Entity
 * @author zw
 * @version 2018-12-11
 */
public class DepartmentManage extends DataEntity<DepartmentManage> {
	
	private static final long serialVersionUID = 1L;
	private String xydm;		// 学院代码
	private String xymc;		// 学院名称
	
	public DepartmentManage() {
		super();
	}

	public DepartmentManage(String id){
		super(id);
	}

	@ExcelField(title="学院代码", align=2, sort=7)
	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	
	@ExcelField(title="学院名称", align=2, sort=8)
	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	
}