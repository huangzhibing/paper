/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.basedata.positionmanagement.web;

import com.google.common.collect.Lists;
import com.hqu.modules.basedata.positionmanagement.entity.ProfessionalTechniqueRank;
import com.hqu.modules.basedata.positionmanagement.service.ProfessionalTechniqueRankService;
import com.hqu.modules.common.service.CommonService;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * 专业技术级别Controller
 * @author zll
 * @version 2018-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/basedata/positionmanagement/professionalTechniqueRank")
public class ProfessionalTechniqueRankController extends BaseController {

	@Autowired
	private ProfessionalTechniqueRankService professionalTechniqueRankService;
	@Autowired
	private CommonService commonService;

	public ProfessionalTechniqueRankController() {
	}

	@ModelAttribute
	public ProfessionalTechniqueRank get(@RequestParam(required=false) String id) {
		ProfessionalTechniqueRank entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = professionalTechniqueRankService.get(id);
		}
		if (entity == null){
			entity = new ProfessionalTechniqueRank();
		}
		return entity;
	}
	
	/**
	 * 专业技术级别列表页面
	 */
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:list")
	@RequestMapping(value = {"list", ""})
	public String list(ProfessionalTechniqueRank professionalTechniqueRank, Model model) {
		model.addAttribute("professionalTechniqueRank", professionalTechniqueRank);
		return "modules/basedata/positionmanagement/professionalTechniqueRankList";
	}
	
		/**
	 * 专业技术级别列表数据
	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(ProfessionalTechniqueRank professionalTechniqueRank, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProfessionalTechniqueRank> page = professionalTechniqueRankService.findPage(new Page<ProfessionalTechniqueRank>(request, response), professionalTechniqueRank); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑专业技术级别表单页面
	 */
	@RequiresPermissions(value={"basedata:positionmanagement:professionalTechniqueRank:view","basedata:positionmanagement:professionalTechniqueRank:add","basedata:positionmanagement:professionalTechniqueRank:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, ProfessionalTechniqueRank professionalTechniqueRank, Model model) {
		model.addAttribute("professionalTechniqueRank", professionalTechniqueRank);
		model.addAttribute("mode", mode);
		return "modules/basedata/positionmanagement/professionalTechniqueRankForm";
	}

	/**
	 * 保存专业技术级别
	 */
	@ResponseBody
	@RequiresPermissions(value={"basedata:positionmanagement:professionalTechniqueRank:add","basedata:positionmanagement:professionalTechniqueRank:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(ProfessionalTechniqueRank professionalTechniqueRank, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(professionalTechniqueRank);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		if (unique(professionalTechniqueRank,"t_professionaltechnical_c","zyjsjbdm")) {
			if(professionalTechniqueRank.getRankCode().matches("^[0-9]{1,10}+$")) {
				professionalTechniqueRankService.save(professionalTechniqueRank);//保存
				j.setSuccess(true);
				j.setMsg("保存专业技术级别成功！");
			} else {
				j.setSuccess(false);
				j.setMsg("代码请填写10位以内数字!");
			}
		}  else {
			j.setSuccess(false);
			j.setMsg("记录已存在！");
		}
//        if (unique(professionalTechniqueRank,"t_professionaltechnical_c","zyjsjbdm") && unique(professionalTechniqueRank,"t_professionaltechnical_c","rankName")) {
//            professionalTechniqueRankService.save(professionalTechniqueRank);//保存
//            j.setSuccess(true);
//            j.setMsg("保存专业技术级别成功！");
//        } else if (unique(professionalTechniqueRank,"t_professionaltechnical_c","zyjsjbdm")){
//            j.setSuccess(false);
//            j.setMsg("记录已存在！");
//        } else {
//            j.setSuccess(false);
//            j.setMsg("记录已存在！");
//        }
		return j;
	}

	//Unique check.
	public boolean unique(ProfessionalTechniqueRank professionalTechniqueRank,String tableName,String filedName) {

		ProfessionalTechniqueRank existProfessionalTechniqueRank = professionalTechniqueRankService.get(professionalTechniqueRank.getId());

		if(existProfessionalTechniqueRank != null){
			if(existProfessionalTechniqueRank.getRankCode().equals(professionalTechniqueRank.getRankCode())) {
				return true;
			}
			else{
				boolean statement1 = commonService.checkOnly(tableName, filedName, professionalTechniqueRank.getRankCode());
				if (statement1) {
					logger.debug("记录已存在！");
					return false;
				} else {
					return true;
				}
			}
		}
		else {
			boolean statement1 = commonService.checkOnly(tableName, filedName, professionalTechniqueRank.getRankCode());
			if (statement1) {
				logger.debug("记录已存在！");
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 删除专业技术级别
	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(ProfessionalTechniqueRank professionalTechniqueRank) {
		AjaxJson j = new AjaxJson();
		professionalTechniqueRankService.delete(professionalTechniqueRank);
		j.setMsg("删除专业技术级别成功");
		return j;
	}
	
	/**
	 * 批量删除专业技术级别
	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			professionalTechniqueRankService.delete(professionalTechniqueRankService.get(id));
		}
		j.setMsg("删除专业技术级别成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(ProfessionalTechniqueRank professionalTechniqueRank, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "专业技术级别"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ProfessionalTechniqueRank> page = professionalTechniqueRankService.findPage(new Page<ProfessionalTechniqueRank>(request, response, -1), professionalTechniqueRank);
    		new ExportExcel("专业技术级别", ProfessionalTechniqueRank.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出专业技术级别记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ProfessionalTechniqueRank> list = ei.getDataList(ProfessionalTechniqueRank.class);
			for (ProfessionalTechniqueRank professionalTechniqueRank : list){
				try{
					professionalTechniqueRankService.save(professionalTechniqueRank);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条专业技术级别记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条专业技术级别记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入专业技术级别失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入专业技术级别数据模板
	 */
	@ResponseBody
	@RequiresPermissions("basedata:positionmanagement:professionalTechniqueRank:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "专业技术级别数据导入模板.xlsx";
    		List<ProfessionalTechniqueRank> list = Lists.newArrayList(); 
    		new ExportExcel("专业技术级别数据", ProfessionalTechniqueRank.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}