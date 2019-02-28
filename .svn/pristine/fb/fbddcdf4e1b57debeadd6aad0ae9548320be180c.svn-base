/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.withdrawcashmanage.web;

import com.google.common.collect.Lists;
import com.hqu.modules.customermanage.ordermanage.entity.OrderManage;
import com.hqu.modules.withdrawcashmanage.service.withdrawmanageService;
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
 * 提现管理Controller
 * @author nzx
 * @version 2018-10-01
 */
@Controller
@RequestMapping(value = "${adminPath}/withdrawmanage/orderManage")
public class withdrawmanageController extends BaseController {

	@Autowired
	private withdrawmanageService withdrawmanageService;
	
	@ModelAttribute
	public OrderManage get(@RequestParam(required=false) String id) {
		OrderManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = withdrawmanageService.get(id);
		}
		if (entity == null){
			entity = new OrderManage();
		}
		return entity;
	}
	
	/**
	 * 订单管理列表页面
	 */
	@RequiresPermissions("ordermanage:orderManage:list")
	@RequestMapping(value = {"list", ""})
	public String list(OrderManage orderManage, Model model) {
		model.addAttribute("orderManage", orderManage);
		return "withdrawcashmanage/withdrawList";
	}
	
		/**
	 * 订单管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(OrderManage orderManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderManage> page = withdrawmanageService.findPage(new Page<OrderManage>(request, response), orderManage);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑订单管理表单页面
	 */
	@RequiresPermissions(value={"ordermanage:orderManage:view","ordermanage:orderManage:add","ordermanage:orderManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, OrderManage orderManage, Model model) {
		model.addAttribute("orderManage", orderManage);
		model.addAttribute("mode", mode);
		return "withdrawcashmanage/withdrawForm";
	}

	/**
	 * 保存订单管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"ordermanage:orderManage:add","ordermanage:orderManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(OrderManage orderManage, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(orderManage);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存

		orderManage.setDdztdm("e");  //状态改为交易成功

		withdrawmanageService.save(orderManage);//保存
		j.setSuccess(true);
		j.setMsg("保存订单管理成功");
		return j;
	}
	
	/**
	 * 删除订单管理
	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(OrderManage orderManage) {
		AjaxJson j = new AjaxJson();
		withdrawmanageService.delete(orderManage);
		j.setMsg("删除订单管理成功");
		return j;
	}
	
	/**
	 * 批量删除订单管理
	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			withdrawmanageService.delete(withdrawmanageService.get(id));
		}
		j.setMsg("删除订单管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(OrderManage orderManage, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "订单管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OrderManage> page = withdrawmanageService.findPage(new Page<OrderManage>(request, response, -1), orderManage);
    		new ExportExcel("订单管理", OrderManage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出订单管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<OrderManage> list = ei.getDataList(OrderManage.class);
			for (OrderManage orderManage : list){
				try{
					withdrawmanageService.save(orderManage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条订单管理记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条订单管理记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入订单管理失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入订单管理数据模板
	 */
	@ResponseBody
	@RequiresPermissions("ordermanage:orderManage:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "订单管理数据导入模板.xlsx";
    		List<OrderManage> list = Lists.newArrayList(); 
    		new ExportExcel("订单管理数据", OrderManage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}