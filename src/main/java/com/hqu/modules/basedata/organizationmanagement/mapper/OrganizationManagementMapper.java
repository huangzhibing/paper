/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.organizationmanagement.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.basedata.organizationmanagement.entity.OrganizationManagement;

import java.util.List;
import java.util.Map;

/**
 * 单位代码表MAPPER接口
 * @author nzx
 * @version 2018-09-09
 */
@MyBatisMapper
public interface OrganizationManagementMapper extends BaseMapper<OrganizationManagement> {
	List<Map<String,String>> orgMap();
	OrganizationManagement getByDWMC(String dwmc);
}