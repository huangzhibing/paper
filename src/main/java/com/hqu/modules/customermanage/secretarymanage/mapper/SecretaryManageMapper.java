/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.secretarymanage.mapper;

import com.hqu.modules.customermanage.secretarymanage.entity.SecretaryManage;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;

/**
 * 学院秘书信息管理MAPPER接口
 * @author ljc
 * @version 2018-12-13
 */
@MyBatisMapper
public interface SecretaryManageMapper extends BaseMapper<SecretaryManage> {
	String getRoleIdByUserId(String userid);
}