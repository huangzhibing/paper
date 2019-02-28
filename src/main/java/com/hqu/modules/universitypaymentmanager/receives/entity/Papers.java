/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.universitypaymentmanager.receives.entity;

import com.hqu.modules.basedata.degreepoint.entity.DegreepointManage;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 论文表Entity
 * @author ylf
 * @version 2018-10-04
 */
public class Papers extends DataEntity<Papers> {
	
	private static final long serialVersionUID = 1L;
	private String LWBH;		// 论文编号
	private String LWMC;		// 论文题目
	private DegreepointManage XSXH;		// 学生学号
	private String LWWJ;		// 论文文件
	private String LWLXDM;		// 论文类型
	private String LWZTDM;		// 论文状态
	private Receives Receives;		// 缴费订单号 父类
	
	public Papers() {
		super();
	}

	public Papers(String id){
		super(id);
	}

	public Papers(Receives Receives){
		this.Receives = Receives;
	}

	@ExcelField(title="论文编号", align=2, sort=7)
	public String getLWBH() {
		return LWBH;
	}

	public void setLWBH(String LWBH) {
		this.LWBH = LWBH;
	}
	
	@ExcelField(title="论文题目", align=2, sort=8)
	public String getLWMC() {
		return LWMC;
	}

	public void setLWMC(String LWMC) {
		this.LWMC = LWMC;
	}
	
	@ExcelField(title="学生学号", fieldType=DegreepointManage.class, value="", align=2, sort=9)
	public DegreepointManage getXSXH() {
		return XSXH;
	}

	public void setXSXH(DegreepointManage XSXH) {
		this.XSXH = XSXH;
	}
	
	@ExcelField(title="论文文件", align=2, sort=10)
	public String getLWWJ() {
		return LWWJ;
	}

	public void setLWWJ(String LWWJ) {
		this.LWWJ = LWWJ;
	}
	
	@ExcelField(title="论文类型", dictType="T_PaperType_C", align=2, sort=11)
	public String getLWLXDM() {
		return LWLXDM;
	}

	public void setLWLXDM(String LWLXDM) {
		this.LWLXDM = LWLXDM;
	}
	
	@ExcelField(title="论文状态", dictType="T_PaperStatus_C", align=2, sort=12)
	public String getLWZTDM() {
		return LWZTDM;
	}

	public void setLWZTDM(String LWZTDM) {
		this.LWZTDM = LWZTDM;
	}
	
	public Receives getReceives() {
		return Receives;
	}

	public void setReceives(Receives Receives) {
		this.Receives = Receives;
	}
	
}