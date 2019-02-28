/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.secretarymanage.entity;

import com.hqu.modules.basedata.university.entity.University;
import javax.validation.constraints.NotNull;
import com.hqu.modules.basedata.department.entity.DepartmentManage;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 学院秘书信息管理Entity
 * @author ljc
 * @version 2018-12-13
 */
public class SecretaryManage extends DataEntity<SecretaryManage> {
	
	private static final long serialVersionUID = 1L;
	private String msdm;		// 秘书代码
	private String msxm;		// 秘书姓名
	private String mslx;		// 秘书类型
	private String yddh;		// 移动电话
	private String yj;		// 邮件
	private University university;		// 高校代码
	private DepartmentManage departmentManage;		// 学院代码
	
	public SecretaryManage() {
		super();
	}

	public SecretaryManage(String id){
		super(id);
	}

	@ExcelField(title="秘书代码", align=2, sort=6)
	public String getMsdm() {
		return msdm;
	}

	public void setMsdm(String msdm) {
		this.msdm = msdm;
	}
	
	@ExcelField(title="秘书姓名", align=2, sort=7)
	public String getMsxm() {
		return msxm;
	}

	public void setMsxm(String msxm) {
		this.msxm = msxm;
	}
	
	@ExcelField(title="秘书类型", align=2, sort=8)
	public String getMslx() {
		return mslx;
	}

	public void setMslx(String mslx) {
		this.mslx = mslx;
	}
	
	@ExcelField(title="移动电话", align=2, sort=9)
	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	
	@ExcelField(title="邮件", align=2, sort=10)
	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}
	
	@NotNull(message="高校代码不能为空")
	@ExcelField(title="高校代码", fieldType=University.class, value="", align=2, sort=11)
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	@ExcelField(title="学院代码", fieldType=DepartmentManage.class, value="", align=2, sort=12)
	public DepartmentManage getDepartmentManage() {
		return departmentManage;
	}

	public void setDepartmentManage(DepartmentManage departmentManage) {
		this.departmentManage = departmentManage;
	}
	
}