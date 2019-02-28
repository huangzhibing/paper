/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.costc.entity;

import com.hqu.modules.basedata.university.entity.University;
import javax.validation.constraints.NotNull;
import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 费用表Entity
 * @author wy
 * @version 2018-10-04
 */
public class Costc extends DataEntity<Costc> {
	
	private static final long serialVersionUID = 1L;
	private University university;		// 高校代码
	private SpecialityManage specialityManage;		// 专业代码
	private Integer psfy;		// 评审费用
	
	public Costc() {
		super();
	}

	public Costc(String id){
		super(id);
	}

	@NotNull(message="高校代码不能为空")
	@ExcelField(title="高校代码", fieldType=University.class, value="", align=2, sort=6)
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	@NotNull(message="专业代码不能为空")
	@ExcelField(title="专业代码", fieldType=SpecialityManage.class, value="", align=2, sort=7)
	public SpecialityManage getSpecialityManage() {
		return specialityManage;
	}

	public void setSpecialityManage(SpecialityManage specialityManage) {
		this.specialityManage = specialityManage;
	}
	
	@ExcelField(title="评审费用", align=2, sort=8)
	public Integer getPsfy() {
		return psfy;
	}

	public void setPsfy(Integer psfy) {
		this.psfy = psfy;
	}
	
}