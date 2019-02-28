package com.hqu.modules.papercount.entity;

import com.hqu.modules.basedata.studentmanage.entity.StudentManage;
import com.hqu.modules.basedata.university.entity.University;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.core.persistence.DataEntity;

import java.util.Date;

public class PaperCount extends DataEntity<PaperCount> {
    private static final long serialVersionUID = 1L;
    private String LWBH;		// 论文编号
    private String LWMC;		// 论文题目
    private StudentManage XSXH;		// 学生学号
    private String LWWJ;		// 论文文件
    private String LWLXDM;		// 论文类型
    private String LWZTDM;		// 论文状态
    private String beginCreateDate;		// 开始 创建时间
    private String endCreateDate;		// 结束 创建时间
    private Date beginUpdateDate;		// 开始 创建时间
    private Date endUpdateDate;		// 结束 创建时间
    private String no;//用户编号
    private String gxmc;//高校名称

    public String getGxmc() {
        return gxmc;
    }

    public void setGxmc(String gxmc) {
        this.gxmc = gxmc;
    }

    public Date getBeginUpdateDate() {
        return beginUpdateDate;
    }

    public void setBeginUpdateDate(Date beginUpdateDate) {
        this.beginUpdateDate = beginUpdateDate;
    }

    public Date getEndUpdateDate() {
        return endUpdateDate;
    }

    public void setEndUpdateDate(Date endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public PaperCount() {
        super();
    }

    public PaperCount(String id){
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

    public String getBeginCreateDate() {
        return beginCreateDate;
    }

    public void setBeginCreateDate(String beginCreateDate) {
        this.beginCreateDate = beginCreateDate;
    }

    public String getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(String endCreateDate) {
        this.endCreateDate = endCreateDate;
    }
}
