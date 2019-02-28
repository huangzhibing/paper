/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.studentmanage.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.basedata.studentmanage.entity.StudentManage;

/**
 * 学生管理MAPPER接口
 * @author ljc
 * @version 2018-09-10
 */
@MyBatisMapper
public interface StudentManageMapper extends BaseMapper<StudentManage> {
	String getMaxIdByMsdm(String para);
}