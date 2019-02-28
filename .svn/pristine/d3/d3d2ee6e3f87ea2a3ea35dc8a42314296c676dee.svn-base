/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.review.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.review.service.ReviewService;
import com.jeeplus.modules.sys.utils.UserUtils;
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


/**
 * 评审记录Controller
 * @author nzx
 * @version 2018-09-07
 */
@Controller
@RequestMapping(value = "${adminPath}/review/paper")
public class ReviewController extends BaseController {

	@Autowired
	private ReviewService reviewService;
	
	@ModelAttribute
	public Paper get(@RequestParam(required=false) String id) {
		Paper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reviewService.get(id);
		}
		if (entity == null){
			entity = new Paper();
		}
		return entity;
	}
	
	/**
	 * 评审记录列表页面
	 */
	@RequiresPermissions("review:paper:list")
	@RequestMapping(value = {"list", ""})
	public String list(Paper paper, Model model) {
		model.addAttribute("paper", paper);
		return "modules/review/paperList";
	}
	
		/**
	 * 评审记录列表数据
	 */
	@ResponseBody
	@RequiresPermissions("review:paper:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Paper paper, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<Paper> page = reviewService.findPage(new Page<Paper>(request, response), paper);

		String userID =  UserUtils.getUser().getLoginName();
		for(int i=0;i<page.getList().size();i++) {
			String pszjNo = page.getList().get(i).getNo();
			if(!(pszjNo.equals(userID))){
				page.getList().remove(i);
				page.setCount(page.getCount()-1);
				i--;
			}
		}
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑评审记录表单页面
	 */
	@RequiresPermissions(value={"review:paper:view","review:paper:add","review:paper:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, Paper paper, Model model) {
		model.addAttribute("paper", paper);
		model.addAttribute("mode", mode);
		if(StringUtils.isBlank(paper.getId())) {
			String paperId = System.currentTimeMillis()+"00000";
			paper.setLWBH(paperId);
		}
		return "modules/review/paperForm";
	}

	/**
	 * 保存评审记录
	 */
	@ResponseBody
	@RequiresPermissions(value={"review:paper:add","review:paper:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Paper paper, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(paper);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		reviewService.save(paper);//保存
		j.setSuccess(true);
		j.setMsg("保存评审记录成功");
		return j;
	}
	
	/**
	 * 删除评审记录
	 */
	@ResponseBody
	@RequiresPermissions("review:paper:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Paper paper) {
		AjaxJson j = new AjaxJson();
		reviewService.delete(paper);
		j.setMsg("删除评审记录成功");
		return j;
	}
	
	/**
	 * 批量删除评审记录
	 */
	@ResponseBody
	@RequiresPermissions("review:paper:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			reviewService.delete(reviewService.get(id));
		}
		j.setMsg("删除评审记录成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("review:paper:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Paper paper, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "评审记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Paper> page = reviewService.findPage(new Page<Paper>(request, response, -1), paper);
    		new ExportExcel("评审记录", Paper.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出评审记录记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("review:paper:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Paper> list = ei.getDataList(Paper.class);
			for (Paper paper : list){
				try{
					reviewService.save(paper);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条评审记录记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条评审记录记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入评审记录失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入评审记录数据模板
	 */
	@ResponseBody
	@RequiresPermissions("review:paper:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "评审记录数据导入模板.xlsx";
    		List<Paper> list = Lists.newArrayList(); 
    		new ExportExcel("评审记录数据", Paper.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}