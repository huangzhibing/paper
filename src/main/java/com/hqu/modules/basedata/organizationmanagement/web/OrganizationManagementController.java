/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.organizationmanagement.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.hqu.modules.common.service.CommonService;
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
import com.hqu.modules.basedata.organizationmanagement.entity.OrganizationManagement;
import com.hqu.modules.basedata.organizationmanagement.service.OrganizationManagementService;

/**
 * 单位代码表Controller
 * @author nzx
 * @version 2018-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/organizationmanagement/organizationManagement")
public class OrganizationManagementController extends BaseController {

	@Autowired
	private OrganizationManagementService organizationManagementService;

	@Autowired
	private CommonService commonService;
	
	@ModelAttribute
	public OrganizationManagement get(@RequestParam(required=false) String id) {
		OrganizationManagement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = organizationManagementService.get(id);
		}
		if (entity == null){
			entity = new OrganizationManagement();
		}
		return entity;
	}
	
	/**
	 * 单位代码表列表页面
	 */
	@RequiresPermissions("organizationmanagement:organizationManagement:list")
	@RequestMapping(value = {"list", ""})
	public String list(OrganizationManagement organizationManagement, Model model) {
		model.addAttribute("organizationManagement", organizationManagement);
		return "basedata/organizationmanagement/organizationManagementList";
	}
	
		/**
	 * 单位代码表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(OrganizationManagement organizationManagement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrganizationManagement> page = organizationManagementService.findPage(new Page<OrganizationManagement>(request, response), organizationManagement); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑单位代码表表单页面
	 */
	@RequiresPermissions(value={"organizationmanagement:organizationManagement:view","organizationmanagement:organizationManagement:add","organizationmanagement:organizationManagement:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, OrganizationManagement organizationManagement, Model model) {
		model.addAttribute("organizationManagement", organizationManagement);
		model.addAttribute("mode", mode);
		return "basedata/organizationmanagement/organizationManagementForm";
	}

	/**
	 * 保存单位代码表
	 */
	@ResponseBody
	@RequiresPermissions(value={"organizationmanagement:organizationManagement:add","organizationmanagement:organizationManagement:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(OrganizationManagement organizationManagement, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(organizationManagement);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		if(checkOnly(organizationManagement,"t_institution_c","dwdm")) {
			organizationManagementService.save(organizationManagement);//保存
			j.setSuccess(true);
			j.setMsg("保存专业代码表成功");
		}
		else{
			j.setSuccess(false);
			j.setMsg("该专业已存在！");
		}
		return j;
	}

	public boolean checkOnly(OrganizationManagement organizationManagement,String tableName,String filedName){

		OrganizationManagement exist = organizationManagementService.get(organizationManagement.getId());
		if(exist != null){
			if(exist.getDwdm().equals(organizationManagement.getDwdm())) {
				return true;
			}
			else{
				boolean statement1 = commonService.checkOnly(tableName, filedName, organizationManagement.getDwdm());
				if (statement1) {
					logger.debug("专业代码已存在");
					return false;
				} else {
					return true;
				}
			}
		}
		else {
			boolean statement1 = commonService.checkOnly(tableName, filedName, organizationManagement.getDwdm());
			if (statement1) {
				logger.debug("专业代码已存在");
				return false;
			} else {
				return true;
			}
		}
	}


	/**
	 * 删除单位代码表
	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(OrganizationManagement organizationManagement) {
		AjaxJson j = new AjaxJson();
		organizationManagementService.delete(organizationManagement);
		j.setMsg("删除单位代码表成功");
		return j;
	}
	
	/**
	 * 批量删除单位代码表
	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			organizationManagementService.delete(organizationManagementService.get(id));
		}
		j.setMsg("删除单位代码表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(OrganizationManagement organizationManagement, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "单位代码表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OrganizationManagement> page = organizationManagementService.findPage(new Page<OrganizationManagement>(request, response, -1), organizationManagement);
    		new ExportExcel("单位代码表", OrganizationManagement.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出单位代码表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<OrganizationManagement> list = ei.getDataList(OrganizationManagement.class);
			for (OrganizationManagement organizationManagement : list){
				try{
					organizationManagementService.save(organizationManagement);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条单位代码表记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条单位代码表记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入单位代码表失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入单位代码表数据模板
	 */
	@ResponseBody
	@RequiresPermissions("organizationmanagement:organizationManagement:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "单位代码表数据导入模板.xlsx";
    		List<OrganizationManagement> list = Lists.newArrayList(); 
    		new ExportExcel("单位代码表数据", OrganizationManagement.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}