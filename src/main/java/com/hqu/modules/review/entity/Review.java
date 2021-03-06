/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.review.entity;

import java.util.Date;
import com.hqu.modules.basedata.degreepoint.entity.DegreepointManage;

import com.hqu.modules.basedata.studentmanage.entity.StudentManage;
import com.hqu.modules.papermanage.entity.Paper;
import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 评审记录Entity
 * @author nzx
 * @version 2018-09-07
 */
public class Review extends DataEntity<Paper> {
	
	private static final long serialVersionUID = 1L;
	private String LWBH;		// 论文编号
	private String LWMC;		// 论文题目
	private StudentManage XSXH;		// 学生学号
	private String LWWJ;		// 论文文件
	private String LWLXDM;		// 论文类型
	private String LWZTDM;		// 论文状态
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public Review() {
		super();
	}

	public Review(String id){
		super(id);
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

	@ExcelField(title="学生学号", fieldType=StudentManage.class, value="XSXH.xsxh", align=2, sort=9)
	public StudentManage getXSXH() {
		return XSXH;
	}


	public void setXSXH(StudentManage XSXH) {
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
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}