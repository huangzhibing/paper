/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.expertmanagement.mapper;

import com.hqu.modules.numericalstatement.web.EC;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 专家信息表MAPPER接口
 * @author 黄志兵
 * @version 2018-08-28
 */
@MyBatisMapper
public interface ExpertMapper extends BaseMapper<Expert> {
    public int selectOneType_Expert(Map<String,Object> objectMap);
    public int selectOneTypeExpert(Map<String,Object> objectMap);

    public int getExpertByDate(Map<String,Object> map);

    public int insertBankInfo(Expert expert);
    public int updateBankInfo(Expert expert);

    Expert getExpertByRand(@Param("expert") Expert expert,@Param("list") List<String> YHZHs);

    List<EC> getExpertByGX(@Param("zjzt") String zjzt);
}