/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.studentmanage.entity;

import com.hqu.modules.basedata.university.entity.University;
import javax.validation.constraints.NotNull;
import com.hqu.modules.basedata.degreepoint.entity.DegreepointManage;
import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 学生管理Entity
 * @author ljc
 * @version 2018-09-10
 */
public class StudentManage extends DataEntity<StudentManage> {
	
	private static final long serialVersionUID = 1L;
	private String xsxh;		// 学生学号
	private String xsxm;		// 学生姓名
	private String yddh;		// 移动电话
	private String yj;		// 邮件
	private String dsxm;		// 导师姓名
	private University university;		// 高校代码
	private DegreepointManage degreepointManage;		// 学位点代码
	private SpecialityManage specialityManage;		// 专业代码
	
	public StudentManage() {
		super();
	}

	public StudentManage(String id){
		super(id);
	}

	@ExcelField(title="学生学号", align=2, sort=6)
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@ExcelField(title="学生姓名", align=2, sort=7)
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	
	@ExcelField(title="移动电话", align=2, sort=8)
	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	
	@ExcelField(title="邮件", align=2, sort=9)
	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}
	
	@ExcelField(title="导师姓名", align=2, sort=10)
	public String getDsxm() {
		return dsxm;
	}

	public void setDsxm(String dsxm) {
		this.dsxm = dsxm;
	}
	
	@NotNull(message="高校代码不能为空")
	@ExcelField(title="高校代码", fieldType=University.class, value="", align=2, sort=11)
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	@NotNull(message="学位点代码不能为空")
	@ExcelField(title="学位点代码", fieldType=DegreepointManage.class, value="", align=2, sort=12)
	public DegreepointManage getDegreepointManage() {
		return degreepointManage;
	}

	public void setDegreepointManage(DegreepointManage degreepointManage) {
		this.degreepointManage = degreepointManage;
	}
	
	@ExcelField(title="专业代码", fieldType=SpecialityManage.class, value="", align=2, sort=13)
	public SpecialityManage getSpecialityManage() {
		return specialityManage;
	}

	public void setSpecialityManage(SpecialityManage specialityManage) {
		this.specialityManage = specialityManage;
	}
	
}