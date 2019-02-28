/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.department.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.hqu.modules.basedata.department.entity.DepartmentManage;
import com.hqu.modules.basedata.department.service.DepartmentManageService;

/**
 * 学院代码Controller
 * @author zw
 * @version 2018-12-11
 */
@Controller
@RequestMapping(value = "${adminPath}/department/departmentManage")
public class DepartmentManageController extends BaseController {

	@Autowired
	private DepartmentManageService departmentManageService;
	
	@ModelAttribute
	public DepartmentManage get(@RequestParam(required=false) String id) {
		DepartmentManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = departmentManageService.get(id);
		}
		if (entity == null){
			entity = new DepartmentManage();
		}
		return entity;
	}
	
	/**
	 * 学院代码列表页面
	 */
	@RequiresPermissions("department:departmentManage:list")
	@RequestMapping(value = {"list", ""})
	public String list(DepartmentManage departmentManage, Model model) {
		model.addAttribute("departmentManage", departmentManage);
		return "basedata/department/departmentManageList";
	}
	
		/**
	 * 学院代码列表数据
	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DepartmentManage departmentManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DepartmentManage> page = departmentManageService.findPage(new Page<DepartmentManage>(request, response), departmentManage); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑学院代码表单页面
	 */
	@RequiresPermissions(value={"department:departmentManage:view","department:departmentManage:add","department:departmentManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, DepartmentManage departmentManage, Model model) {
		model.addAttribute("departmentManage", departmentManage);
		model.addAttribute("mode", mode);
		return "basedata/department/departmentManageForm";
	}

	/**
	 * 保存学院代码
	 */
	@ResponseBody
	@RequiresPermissions(value={"department:departmentManage:add","department:departmentManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DepartmentManage departmentManage, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(departmentManage);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		departmentManageService.save(departmentManage);//保存
		j.setSuccess(true);
		j.setMsg("保存学院代码成功");
		return j;
	}
	
	/**
	 * 删除学院代码
	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DepartmentManage departmentManage) {
		AjaxJson j = new AjaxJson();
		departmentManageService.delete(departmentManage);
		j.setMsg("删除学院代码成功");
		return j;
	}
	
	/**
	 * 批量删除学院代码
	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			departmentManageService.delete(departmentManageService.get(id));
		}
		j.setMsg("删除学院代码成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(DepartmentManage departmentManage, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学院代码"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DepartmentManage> page = departmentManageService.findPage(new Page<DepartmentManage>(request, response, -1), departmentManage);
    		new ExportExcel("学院代码", DepartmentManage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出学院代码记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DepartmentManage> list = ei.getDataList(DepartmentManage.class);
			for (DepartmentManage departmentManage : list){
				try{
					departmentManageService.save(departmentManage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条学院代码记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条学院代码记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入学院代码失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入学院代码数据模板
	 */
	@ResponseBody
	@RequiresPermissions("department:departmentManage:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学院代码数据导入模板.xlsx";
    		List<DepartmentManage> list = Lists.newArrayList(); 
    		new ExportExcel("学院代码数据", DepartmentManage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}