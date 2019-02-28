/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.universitypaymentmanager.receives.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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
import com.hqu.modules.universitypaymentmanager.receives.entity.Receives;
import com.hqu.modules.universitypaymentmanager.receives.service.ReceivesService;

/**
 * 订单管理Controller
 * @author ylf
 * @version 2018-10-04
 */
@Controller
@RequestMapping(value = "${adminPath}/receives/receives")
public class ReceivesController extends BaseController {

	@Autowired
	private ReceivesService receivesService;
	
	@ModelAttribute
	public Receives get(@RequestParam(required=false) String id) {
		Receives entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = receivesService.get(id);
		}
		if (entity == null){
			entity = new Receives();
		}
		return entity;
	}
	
	/**
	 * 论文接收列表页面
	 */

	@RequiresPermissions("receives:receives:list")
	@RequestMapping(value = {"list", ""})
	public String list(Receives receives, Model model) {
		model.addAttribute("receives", receives);
		return "universitypaymentmanager/receives/receivesList";
	}
	@ResponseBody
	@RequestMapping(value = {"commitSelect_All"})
	public AjaxJson makeSure(@RequestParam("ids")List<String> ids) {
		AjaxJson j = new AjaxJson();
		for(String id:ids){
			receivesService.makeSure(id);
	}
		j.setSuccess(true);
		j.setMsg("订单交易成功！");
		return j;
	}
	
		/**
	 * 论文接收列表数据
	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Receives receives, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Receives> page = receivesService.findPage(new Page<Receives>(request, response), receives);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑论文接收表单页面
	 */
	@RequiresPermissions(value={"receives:receives:view","receives:receives:add","receives:receives:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, Receives receives, Model model) {
		model.addAttribute("receives", receives);
		model.addAttribute("mode", mode);
		return "universitypaymentmanager/receives/receivesForm";
	}

	/**
	 * 保存论文接收
	 */
	@ResponseBody
	@RequiresPermissions(value={"receives:receives:add","receives:receives:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Receives receives, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(receives);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		receivesService.save(receives);//保存
		j.setSuccess(true);
		j.setMsg("保存论文接收成功");
		return j;
	}
	
	/**
	 * 删除论文接收
	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Receives receives) {
		AjaxJson j = new AjaxJson();
		receivesService.delete(receives);
		j.setMsg("删除论文接收成功");
		return j;
	}
	
	/**
	 * 批量删除论文接收
	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			receivesService.delete(receivesService.get(id));
		}
		j.setMsg("删除论文接收成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Receives receives, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "论文接收"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Receives> page = receivesService.findPage(new Page<Receives>(request, response, -1), receives);
    		new ExportExcel("论文接收", Receives.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出论文接收记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public Receives detail(String id) {
		return receivesService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Receives> list = ei.getDataList(Receives.class);
			for (Receives receives : list){
				try{
					receivesService.save(receives);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条论文接收记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条论文接收记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入论文接收失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入论文接收数据模板
	 */
	@ResponseBody
	@RequiresPermissions("receives:receives:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "论文接收数据导入模板.xlsx";
    		List<Receives> list = Lists.newArrayList(); 
    		new ExportExcel("论文接收数据", Receives.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }
	

}