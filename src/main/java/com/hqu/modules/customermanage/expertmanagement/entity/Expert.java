/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.expertmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hqu.modules.basedata.positionmanagement.entity.ProfessionalTechniqueRank;
import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;
import com.jeeplus.common.utils.base.annotation.NotNull;
import com.jeeplus.modules.sys.entity.Area;
import com.hqu.modules.basedata.organizationmanagement.entity.OrganizationManagement;
import com.hqu.modules.basedata.university.entity.University;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.mapper.ExpertMapper;

/**
 * 专家信息表Entity
 * @author 黄志兵
 * @version 2018-08-28
 */
public class Expert extends DataEntity<Expert> {
	
	private static final long serialVersionUID = 1L;
	private String YHZH;		// 用户账号
	private String ZJXM;		// 专家姓名
	private String ZJXLDM;		// 专家类型代码
	private ProfessionalTechniqueRank ZYJSZWDM;		// 专业知识职务代码
	private String DSJBDM;		// 导师级别代码
	private String XBDM;		// 性别代码
	private Area QYDM;		// 区域代码
	private String DZYX;		// 电子邮箱
	private OrganizationManagement DWDM;		// 单位代码
	private Date CSNY;		// 出生年月
	private String EJXY;		// 二级学院
	private String ZJXW;		// 最高学位
	private University BYYXDM;		// 毕业院校代码
	private String WYYZ;		// 外语语种
	private String WYSXCD;		// 外语熟悉程度
	private String YDDH;		// 移动电话
	private String LXDH;		// 联系电话
	private String LXDZ;		// 联系地址
	private String YB;		// 邮编
	private String JG;		// 籍贯
	private String ZJZTDM;		// 专家状态代码
	private SpecialityManage specialityManage; //专业代码

	private Integer pscs;//评审次数

	private Double YE;    //余额

	private String pszt; //评审状态

	private String sfzh;		//身份证号
	private String yhkh;		//银行卡号
	private String khh;			//开户行
	
	public Integer getPscs() {
		return pscs;
	}

	public void setPscs(Integer pscs) {
		this.pscs = pscs;
	}


	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}


	public Expert() {
		super();
	}

	public Expert(String id){
		super(id);
	}

	@ExcelField(title="用户账号", align=2, sort=1)
	public String getYHZH() {
		return YHZH;
	}

	public void setYHZH(String YHZH) {
		this.YHZH = YHZH;
	}
	
	@ExcelField(title="专家姓名", align=2, sort=2)
	public String getZJXM() {
		return ZJXM;
	}

	public void setZJXM(String ZJXM) {
		this.ZJXM = ZJXM;
	}
	
	@ExcelField(title="专家类型代码", dictType="T_ExpertType_C", align=2, sort=3)
	public String getZJXLDM() {
		return ZJXLDM;
	}

	public void setZJXLDM(String ZJXLDM) {
		this.ZJXLDM = ZJXLDM;
	}
	
	@ExcelField(title="专业知识职务代码", fieldType=ProfessionalTechniqueRank.class, value="", align=2, sort=4)
	public ProfessionalTechniqueRank getZYJSZWDM() {
		return ZYJSZWDM;
	}

	public void setZYJSZWDM(ProfessionalTechniqueRank ZYJSZWDM) {
		this.ZYJSZWDM = ZYJSZWDM;
	}
	
	@ExcelField(title="导师级别代码", dictType="T_TutorLevel_C", align=2, sort=5)
	public String getDSJBDM() {
		return DSJBDM;
	}

	public void setDSJBDM(String DSJBDM) {
		this.DSJBDM = DSJBDM;
	}
	
	@ExcelField(title="性别代码", dictType="T_SexType_C", align=2, sort=6)
	public String getXBDM() {
		return XBDM;
	}

	public void setXBDM(String XBDM) {
		this.XBDM = XBDM;
	}
	
	@ExcelField(title="区域代码", fieldType=Area.class, value="", align=2, sort=7)
	public Area getQYDM() {
		return QYDM;
	}

	public void setQYDM(Area QYDM) {
		this.QYDM = QYDM;
	}
	
	@ExcelField(title="电子邮箱", align=2, sort=8)
	public String getDZYX() {
		return DZYX;
	}

	public void setDZYX(String DZYX) {
		this.DZYX = DZYX;
	}
	
	@ExcelField(title="单位代码", fieldType=OrganizationManagement.class, value="", align=2, sort=9)
	public OrganizationManagement getDWDM() {
		return DWDM;
	}

	public void setDWDM(OrganizationManagement DWDM) {
		this.DWDM = DWDM;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="出生年月", align=2, sort=10)
	public Date getCSNY() {
		return CSNY;
	}

	public void setCSNY(Date CSNY) {
		this.CSNY = CSNY;
	}
	
	@ExcelField(title="二级学院", align=2, sort=11)
	public String getEJXY() {
		return EJXY;
	}

	public void setEJXY(String EJXY) {
		this.EJXY = EJXY;
	}
	
	@ExcelField(title="最高学位", align=2, sort=12)
	public String getZJXW() {
		return ZJXW;
	}

	public void setZJXW(String ZJXW) {
		this.ZJXW = ZJXW;
	}
	
	@ExcelField(title="毕业院校代码", fieldType=University.class, value="", align=2, sort=13)
	public University getBYYXDM() {
		return BYYXDM;
	}

	public void setBYYXDM(University BYYXDM) {
		this.BYYXDM = BYYXDM;
	}
	
	@ExcelField(title="外语语种", align=2, sort=14)
	public String getWYYZ() {
		return WYYZ;
	}

	public void setWYYZ(String WYYZ) {
		this.WYYZ = WYYZ;
	}
	
	@ExcelField(title="外语熟悉程度", align=2, sort=15)
	public String getWYSXCD() {
		return WYSXCD;
	}

	public void setWYSXCD(String WYSXCD) {
		this.WYSXCD = WYSXCD;
	}
	
	@ExcelField(title="移动电话", align=2, sort=16)
	@Length(max=11,min=11,message="移动电话长度必须为11位")
	public String getYDDH() {
		return YDDH;
	}

	public void setYDDH(String YDDH) {
		this.YDDH = YDDH;
	}
	
	@ExcelField(title="联系电话", align=2, sort=17)
	public String getLXDH() {
		return LXDH;
	}

	public void setLXDH(String LXDH) {
		this.LXDH = LXDH;
	}
	
	@ExcelField(title="联系地址", align=2, sort=18)
	public String getLXDZ() {
		return LXDZ;
	}

	public void setLXDZ(String LXDZ) {
		this.LXDZ = LXDZ;
	}
	
	@ExcelField(title="邮编", align=2, sort=19)
	public String getYB() {
		return YB;
	}

	public void setYB(String YB) {
		this.YB = YB;
	}

	@ExcelField(title="余额",align = 2,sort = 20)
	public Double getYE() {
		return YE;
	}

	public void setYE(Double YE) {
		this.YE = YE;
	}


	@ExcelField(title="籍贯", align=2, sort=21)
	public String getJG() {
		return JG;
	}

	public void setJG(String JG) {
		this.JG = JG;
	}
	
	@ExcelField(title="专家状态代码", dictType="T_ExpertStatus_C", align=2, sort=22)
	public String getZJZTDM() {
		return ZJZTDM;
	}

	public void setZJZTDM(String ZJZTDM) {
		this.ZJZTDM = ZJZTDM;
	}

	@ExcelField(title = "专业代码",fieldType=SpecialityManage.class,value="",align = 2,sort = 23)
	public SpecialityManage getSpecialityManage() {
		if(specialityManage==null)
			specialityManage=new SpecialityManage();
		return specialityManage;
	}

	public void setSpecialityManage(SpecialityManage specialityManage) {
		this.specialityManage = specialityManage;
	}

	public String getPszt() {
		return pszt;
	}

	public void setPszt(String pszt) {
		this.pszt = pszt;
	}








}