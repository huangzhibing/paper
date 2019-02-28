/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.feedbackmanage.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.hqu.modules.basedata.feedback.entity.Feedback;
import com.jeeplus.modules.sys.utils.UserUtils;
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
import com.hqu.modules.feedbackmanage.entity.Feedbackmanage;
import com.hqu.modules.feedbackmanage.service.FeedbackmanageService;
import com.hqu.modules.basedata.feedback.service.FeedbackService;
import static java.lang.Integer.parseInt;

/**
 * 反馈Controller
 * @author wdz
 * @version 2018-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/feedbackmanage/feedbackmanage")
public class FeedbackmanageController extends BaseController {

	@Autowired
	private FeedbackmanageService feedbackmanageService;
	@Autowired
	private FeedbackService feedbackService;
	@ModelAttribute
	public Feedbackmanage get(@RequestParam(required=false) String id) {
		Feedbackmanage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = feedbackmanageService.get(id);
		}
		if (entity == null){
			entity = new Feedbackmanage();
		}
		return entity;
	}
	
	/**
	 * 反馈列表页面
	 */
	@RequiresPermissions("feedbackmanage:feedbackmanage:list")
	@RequestMapping(value = {"list", ""})
	public String list(Feedbackmanage feedbackmanage, Model model) {
		model.addAttribute("feedbackmanage", feedbackmanage);
		return "modules/feedbackmanage/feedbackmanageList";
	}
	
		/**
	 * 反馈列表数据
	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Feedbackmanage feedbackmanage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Feedbackmanage> page = feedbackmanageService.findPage(new Page<Feedbackmanage>(request, response), feedbackmanage); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑反馈表单页面
	 */
	@RequiresPermissions(value={"feedbackmanage:feedbackmanage:view","feedbackmanage:feedbackmanage:add","feedbackmanage:feedbackmanage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, Feedbackmanage feedbackmanage, Model model,@RequestParam(value="id",required = false) String id) {
		Feedbackmanage feedback1=get(id);
		feedbackmanage.setFklsh(feedback1.getFklsh());
		feedbackmanage.setFksj(feedback1.getFksj());
        feedbackmanage.setHfr(UserUtils.getUser().getName());
		model.addAttribute("feedbackmanage", feedbackmanage);
		model.addAttribute("mode", mode);
		return "modules/feedbackmanage/feedbackmanageForm";
	}

	/**
	 * 保存反馈
	 */
	@ResponseBody
	@RequiresPermissions(value={"feedbackmanage:feedbackmanage:add","feedbackmanage:feedbackmanage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Feedbackmanage feedbackmanage, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(feedbackmanage);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
        feedbackmanage.setHfsj(new Date());
        feedbackmanage.setHfr(UserUtils.getUser().getLoginName());
		feedbackmanageService.save(feedbackmanage);//保存
		j.setSuccess(true);
		j.setMsg("回复反馈成功");
		return j;
	}
	
	/**
	 * 删除反馈
	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Feedbackmanage feedbackmanage) {
		AjaxJson j = new AjaxJson();
		feedbackmanageService.delete(feedbackmanage);
		j.setMsg("删除反馈成功");
		return j;
	}
	
	/**
	 * 批量删除反馈
	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			feedbackmanageService.delete(feedbackmanageService.get(id));
		}
		j.setMsg("删除反馈成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Feedbackmanage feedbackmanage, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "反馈"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Feedbackmanage> page = feedbackmanageService.findPage(new Page<Feedbackmanage>(request, response, -1), feedbackmanage);
    		new ExportExcel("反馈", Feedbackmanage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出反馈记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Feedbackmanage> list = ei.getDataList(Feedbackmanage.class);
			for (Feedbackmanage feedbackmanage : list){
				try{
					feedbackmanageService.save(feedbackmanage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条反馈记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条反馈记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入反馈失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入反馈数据模板
	 */
	@ResponseBody
	@RequiresPermissions("feedbackmanage:feedbackmanage:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "反馈数据导入模板.xlsx";
    		List<Feedbackmanage> list = Lists.newArrayList(); 
    		new ExportExcel("反馈数据", Feedbackmanage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}