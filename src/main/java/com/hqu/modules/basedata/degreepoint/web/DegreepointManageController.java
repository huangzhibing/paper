/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.degreepoint.web;

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
import com.hqu.modules.basedata.degreepoint.entity.DegreepointManage;
import com.hqu.modules.basedata.degreepoint.service.DegreepointManageService;

/**
 * 学位点代码表Controller
 * @author 张卫
 * @version 2018-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/degreepoint/degreepointManage")
public class DegreepointManageController extends BaseController {

	@Autowired
	private DegreepointManageService degreepointManageService;
	
	@ModelAttribute
	public DegreepointManage get(@RequestParam(required=false) String id) {
		DegreepointManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = degreepointManageService.get(id);
		}
		if (entity == null){
			entity = new DegreepointManage();
		}
		return entity;
	}
	
	/**
	 * 学点代码表列表页面
	 */
	@RequiresPermissions("degreepoint:degreepointManage:list")
	@RequestMapping(value = {"list", ""})
	public String list(DegreepointManage degreepointManage, Model model) {
		model.addAttribute("degreepointManage", degreepointManage);
		return "basedata/degreepoint/degreepointManageList";
	}
	
		/**
	 * 学点代码表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DegreepointManage degreepointManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DegreepointManage> page = degreepointManageService.findPage(new Page<DegreepointManage>(request, response), degreepointManage); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑学点代码表表单页面
	 */
	@RequiresPermissions(value={"degreepoint:degreepointManage:view","degreepoint:degreepointManage:add","degreepoint:degreepointManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, DegreepointManage degreepointManage, Model model) {
		model.addAttribute("degreepointManage", degreepointManage);
		model.addAttribute("mode", mode);
		return "basedata/degreepoint/degreepointManageForm";
	}

	/**
	 * 保存学点代码表
	 */
	@ResponseBody
	@RequiresPermissions(value={"degreepoint:degreepointManage:add","degreepoint:degreepointManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DegreepointManage degreepointManage, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(degreepointManage);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		degreepointManageService.save(degreepointManage);//保存
		j.setSuccess(true);
		j.setMsg("保存学点代码表成功");
		return j;
	}
	
	/**
	 * 删除学点代码表
	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DegreepointManage degreepointManage) {
		AjaxJson j = new AjaxJson();
		degreepointManageService.delete(degreepointManage);
		j.setMsg("删除学点代码表成功");
		return j;
	}
	
	/**
	 * 批量删除学点代码表
	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			degreepointManageService.delete(degreepointManageService.get(id));
		}
		j.setMsg("删除学点代码表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(DegreepointManage degreepointManage, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学点代码表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DegreepointManage> page = degreepointManageService.findPage(new Page<DegreepointManage>(request, response, -1), degreepointManage);
    		new ExportExcel("学点代码表", DegreepointManage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出学点代码表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DegreepointManage> list = ei.getDataList(DegreepointManage.class);
			for (DegreepointManage degreepointManage : list){
				try{
					degreepointManageService.save(degreepointManage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条学点代码表记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条学点代码表记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入学点代码表失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入学点代码表数据模板
	 */
	@ResponseBody
	@RequiresPermissions("degreepoint:degreepointManage:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学点代码表数据导入模板.xlsx";
    		List<DegreepointManage> list = Lists.newArrayList(); 
    		new ExportExcel("学点代码表数据", DegreepointManage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}