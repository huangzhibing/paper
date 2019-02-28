/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.costc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.hqu.modules.common.service.CommonService;
import com.jeeplus.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.hqu.modules.basedata.costc.entity.Costc;
import com.hqu.modules.basedata.costc.service.CostcService;

/**
 * 费用表Controller
 * @author wy
 * @version 2018-10-04
 */
@Controller
@RequestMapping(value = "${adminPath}/costc/costC")
public class CostcController extends BaseController {

	@Autowired
	private CostcService costCService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public Costc get(@RequestParam(required=false) String id) {
		Costc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = costCService.get(id);
		}
		if (entity == null){
			entity = new Costc();
		}
		return entity;
	}
	
	/**
	 * 费用表列表页面
	 */
	@RequiresPermissions("costc:costC:list")
	@RequestMapping(value = {"list", ""})
	public String list(Costc costC, Model model) {
		model.addAttribute("costC", costC);
		return "basedata/costc/costCList";
	}
	
		/**
	 * 费用表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Costc costC, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Costc> page = costCService.findPage(new Page<Costc>(request, response), costC);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑费用表表单页面
	 */
	@RequiresPermissions(value={"costc:costC:view","costc:costC:add","costc:costC:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, Costc costC, Model model) {
		model.addAttribute("costC", costC);
		model.addAttribute("mode", mode);
		return "basedata/costc/costCForm";
	}

	/**
	 * 保存费用表
	 */
	@ResponseBody
	@RequiresPermissions(value={"costc:costC:add","costc:costC:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Costc costC, Model model) throws Exception {
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(costC);
		if (StringUtils.isNotBlank(errMsg)) {
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		costCService.save(costC);//保存
		j.setSuccess(true);
		j.setMsg("保存费用表成功");
		return j;
	}







	
	/**
	 * 删除费用表
	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Costc costC) {
		AjaxJson j = new AjaxJson();
		costCService.delete(costC);
		j.setMsg("删除费用表成功");
		return j;
	}
	
	/**
	 * 批量删除费用表
	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			costCService.delete(costCService.get(id));
		}
		j.setMsg("删除费用表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Costc costC, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "费用表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Costc> page = costCService.findPage(new Page<Costc>(request, response, -1), costC);
    		new ExportExcel("费用表", Costc.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出费用表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Costc> list = ei.getDataList(Costc.class);
			for (Costc costC : list){
				try{
					costCService.save(costC);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条费用表记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条费用表记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入费用表失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入费用表数据模板
	 */
	@ResponseBody
	@RequiresPermissions("costc:costC:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "费用表数据导入模板.xlsx";
    		List<Costc> list = Lists.newArrayList();
    		new ExportExcel("费用表数据", Costc.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}