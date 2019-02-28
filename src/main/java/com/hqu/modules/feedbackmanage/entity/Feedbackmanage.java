/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.feedbackmanage.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 反馈Entity
 * @author wdz
 * @version 2018-09-09
 */
public class Feedbackmanage extends DataEntity<Feedbackmanage> {
	
	private static final long serialVersionUID = 1L;
	private String fklsh;		// 反馈流水号
	private String fklxdm;		// 反馈类型代码
	private Date fksj;		// 发生时间
	private String fknr;		// 反馈内容
	private String fkr;		// 反馈人
	private Date hfsj;		// 回复时间
	private String hfnr;		// 回复内容
	private String hfr;		// 回复人
	
	public Feedbackmanage() {
		super();
	}

	public Feedbackmanage(String id){
		super(id);
	}

	@ExcelField(title="反馈流水号", align=2, sort=7)
	public String getFklsh() {
		return fklsh;
	}

	public void setFklsh(String fklsh) {
		this.fklsh = fklsh;
	}
	
	@ExcelField(title="反馈类型代码", dictType="T_FeedBackType_C", align=2, sort=8)
	public String getFklxdm() {
		return fklxdm;
	}

	public void setFklxdm(String fklxdm) {
		this.fklxdm = fklxdm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发生时间", align=2, sort=9)
	public Date getFksj() {
		return fksj;
	}

	public void setFksj(Date fksj) {
		this.fksj = fksj;
	}
	
	@ExcelField(title="反馈内容", align=2, sort=10)
	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}
	
	@ExcelField(title="反馈人", align=2, sort=11)
	public String getFkr() {
		return fkr;
	}

	public void setFkr(String fkr) {
		this.fkr = fkr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="回复时间", align=2, sort=12)
	public Date getHfsj() {
		return hfsj;
	}

	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}
	
	@ExcelField(title="回复内容", align=2, sort=13)
	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}
	
	@ExcelField(title="回复人", align=2, sort=14)
	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}
	
}