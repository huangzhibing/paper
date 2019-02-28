/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.papergrade.service;

import com.hqu.modules.papergrade.entity.PaperGrade;
import com.hqu.modules.papergrade.mapper.PaperGradeMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论文等级统计Service
 * @author zll
 * @version 2018-10-12
 */
@Service
@Transactional(readOnly = true)
public class PaperGradeService extends CrudService<PaperGradeMapper, PaperGrade> {
    @Autowired
    PaperGradeMapper paperGradeMapper;

    public PaperGrade get(String id) {
		return super.get(id);
	}
	
	public List<PaperGrade> findList(PaperGrade paperGrade) {
		return super.findList(paperGrade);
	}
	
	public Page<PaperGrade> findPage(Page<PaperGrade> page, PaperGrade paperGrade) {
		return super.findPage(page, paperGrade);
	}
	
	@Transactional(readOnly = false)
	public void save(PaperGrade paperGrade) {
		super.save(paperGrade);
	}
	
	@Transactional(readOnly = false)
	public void delete(PaperGrade paperGrade) {
		super.delete(paperGrade);
	}

    public int getNumByDate(String beginDate, String endDate, String tableName, String filedName1,String data){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("tableName",tableName);
        objectMap.put("filedName1",filedName1);
        objectMap.put("data",data);
        objectMap.put("beginDate","'"+beginDate+"'");
        objectMap.put("endDate","'"+endDate+"'");

        return paperGradeMapper.getNum(objectMap);

    }
}