/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.customermanage.secretarymanage.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.hqu.modules.common.service.CommonService;
import com.hqu.modules.customermanage.secretarymanage.mapper.SecretaryManageMapper;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.SystemService;
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
import com.hqu.modules.customermanage.secretarymanage.entity.SecretaryManage;
import com.hqu.modules.customermanage.secretarymanage.service.SecretaryManageService;

/**
 * 学院秘书信息管理Controller
 * @author ljc
 * @version 2018-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/secretarymanage/secretaryManage")
public class SecretaryManageController extends BaseController {

	@Autowired
	private SecretaryManageService secretaryManageService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private SecretaryManageMapper secretaryManageMapper;

	@ModelAttribute
	public SecretaryManage get(@RequestParam(required=false) String id) {
		SecretaryManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secretaryManageService.get(id);
		}
		if (entity == null){
			entity = new SecretaryManage();
		}
		return entity;
	}
	
	/**
	 * 学院秘书信息列表页面
	 */
	@RequiresPermissions("secretarymanage:secretaryManage:list")
	@RequestMapping(value = {"list", ""})
	public String list(SecretaryManage secretaryManage, Model model) {
		model.addAttribute("secretaryManage", secretaryManage);
		return "customermanage/secretarymanage/secretaryManageList";
	}

	@RequiresPermissions("secretarymanage:secretaryManage:list")
	@RequestMapping(value = {"departmentsecretaryList"})
	public String departmentsecretaryList() {
		return "customermanage/secretarymanage/departmentsecretaryManageList";
	}


		/**
	 * 学院秘书信息列表数据
	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SecretaryManage secretaryManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecretaryManage> page = secretaryManageService.findPage(new Page<SecretaryManage>(request, response), secretaryManage); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑学院秘书信息表单页面
	 */
	@RequiresPermissions(value={"secretarymanage:secretaryManage:view","secretarymanage:secretaryManage:add","secretarymanage:secretaryManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, String type , SecretaryManage secretaryManage, Model model) {
		if (StringUtils.isBlank(secretaryManage.getMsdm())){
				secretaryManage.setMslx("G");
		}
		model.addAttribute("secretaryManage", secretaryManage);
		model.addAttribute("mode", mode);
		return "customermanage/secretarymanage/secretaryManageForm";
	}

	@RequiresPermissions(value={"secretarymanage:secretaryManage:view","secretarymanage:departmentsecretaryManage:add","secretarymanage:secretaryManage:add","secretarymanage:secretaryManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "departmentsecretaryManageForm/{mode}")
	public String departmentsecretaryManageForm(@PathVariable String mode, String type , SecretaryManage secretaryManage, Model model) {
		if (StringUtils.isBlank(secretaryManage.getMsdm())){
			secretaryManage.setMslx("X");
		}
		model.addAttribute("secretaryManage", secretaryManage);
		model.addAttribute("mode", mode);
		return "customermanage/secretarymanage/departmentsecretaryManageForm";
	}
//	@RequiresPermissions(value={"secretarymanage:secretaryManage:view","secretarymanage:secretaryManage:add","secretarymanage:secretaryManage:edit" }, logical = Logical.OR)
//	@RequestMapping(value = "departmentsecretaryManageForm")
//	public String departmentsecretaryManageForm(@PathVariable String mode,SecretaryManage secretaryManage, Model model) {
//		if (StringUtils.isBlank(secretaryManage.getMsdm())) {
//			secretaryManage.setMslx("X");
//		}
//
//		model.addAttribute("secretaryManage", secretaryManage);
//		model.addAttribute("mode", mode);
//		return "customermanage/secretarymanage/departmentsecretaryManageForm";
//	}
	/**
	 * 保存学院秘书信息
	 */
	@ResponseBody
	@RequiresPermissions(value={"secretarymanage:secretaryManage:add","secretarymanage:secretaryManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SecretaryManage secretaryManage,String type, HttpServletRequest request,Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(secretaryManage);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		String gxdm_input = request.getParameter("gxdm_input");
		String msdm_input = request.getParameter("msdm");
		if (!checkcountname(msdm_input)){
			j.setSuccess(false);
			j.setMsg("秘书代码不能包含中文，请重新输入");
			return j;
		}
		if (msdm_input.length()!=6){
			j.setSuccess(false);
			j.setMsg("秘书代码必须设置为6位，请重新输入");
			return j;
		}
		if (gxdm_input == null){
			j.setSuccess(false);
			j.setMsg("高校代码不能为空，请选择用户所在高校");
			return j;
		}
		String yddh_input = request.getParameter("yddh");
		String yj_input = request.getParameter("yj");
		if (yddh_input == null || yddh_input.length() != 11){
			j.setSuccess(false);
			j.setMsg("请输入正确11位移动电话");
			return j;
		}
		if (yj_input == null){
			j.setSuccess(false);
			j.setMsg("请输入邮件地址");
			return j;
		}
		int yj_result = yj_input.indexOf("@");
		if (yj_result == -1){
			j.setSuccess(false);
			j.setMsg("请输入正确的邮件地址");
			return j;
		}
		secretaryManage.setMsdm(gxdm_input+msdm_input);
		if (checkOnly(secretaryManage,"t_secretary_manage","msdm")){
			User checkUserExisted = systemService.getUserByLoginName(secretaryManage.getMsdm());
			if (null == checkUserExisted){
				User user = new User();
				Office office = new Office();
				Office company = new Office();
				List<Role> roleList = new ArrayList<>();
				if (secretaryManage.getMslx().equals("X")){

					office.setId(Global.getConfig("DEPARTMENTSECRETARY_OFFICE_ID"));
					user.setOffice(office);

					company.setId(Global.getConfig("DEPARTMENTSECRETARY_COMPANY_ID"));
					user.setCompany(company);

					Role role = systemService.getRole(Global.getConfig("DEPARTMENTSECRETARY_ROLE_ID"));
					user.setRole(role);
					roleList.add(role);
				}else {
					office.setId(Global.getConfig("SECRETARY_OFFICE_ID"));
					user.setOffice(office);

					company.setId(Global.getConfig("SECRETARY_COMPANY_ID"));
					user.setCompany(company);

					Role role = systemService.getRole(Global.getConfig("SECRETARY_ROLE_ID"));
					user.setRole(role);
					roleList.add(role);
				}
				secretaryManageService.save(secretaryManage);
				user.setPhoto("/paper/userfiles/1/程序附件//photo/2018/9/timg.jpg");

				user.setLoginName(secretaryManage.getMsdm());
				user.setPassword(systemService.entryptPassword(Global.getConfig("SECRETARY_PASSWORD")));
				user.setName(secretaryManage.getMsxm());

				user.setRoleList(roleList);

				systemService.saveUser(user);
				UserUtils.clearCache(user);
				model.addAttribute("type", type);
			}
			j.setSuccess(true);
			j.setMsg("保存秘书信息成功,默认密码为123456");
		}else {
			j.setSuccess(false);
			j.setMsg("秘书账号已存在！");
		}
		return j;

	}


	//唯一性检测
	public boolean checkOnly(SecretaryManage secretaryManage, String tableName, String filedName){
		SecretaryManage existedSecretary = secretaryManageService.get(secretaryManage.getId());
		if(existedSecretary != null){
			if(existedSecretary.getMsdm().equals(secretaryManage.getMsdm())) {
				return true;
			}
			else{
				boolean statement1 = commonService.checkOnly(tableName, filedName, secretaryManage.getMsdm());
				if (statement1) {
					logger.debug("编码已存在");
					return false;
				} else {
					return true;
				}
			}
		}
		else {
			boolean statement1 = commonService.checkOnly(tableName, filedName, secretaryManage.getMsdm());
			if (statement1) {
				logger.debug("编码已存在");
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 删除学院秘书信息
	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SecretaryManage secretaryManage) {
		AjaxJson j = new AjaxJson();
		secretaryManageService.delete(secretaryManage);
		j.setMsg("删除学院秘书信息成功");
		return j;
	}
	
	/**
	 * 批量删除学院秘书信息
	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			secretaryManageService.delete(secretaryManageService.get(id));
		}
		j.setMsg("删除学院秘书信息成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(SecretaryManage secretaryManage, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学院秘书信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SecretaryManage> page = secretaryManageService.findPage(new Page<SecretaryManage>(request, response, -1), secretaryManage);
    		new ExportExcel("学院秘书信息", SecretaryManage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出学院秘书信息记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SecretaryManage> list = ei.getDataList(SecretaryManage.class);
			for (SecretaryManage secretaryManage : list){
				try{
					secretaryManageService.save(secretaryManage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条学院秘书信息记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条学院秘书信息记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入学院秘书信息失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入学院秘书信息数据模板
	 */
	@ResponseBody
	@RequiresPermissions("secretarymanage:secretaryManage:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "学院秘书信息数据导入模板.xlsx";
    		List<SecretaryManage> list = Lists.newArrayList(); 
    		new ExportExcel("学院秘书信息数据", SecretaryManage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }
	//判断输入是否存在汉字
	public boolean checkcountname(String countname)
	{
		AjaxJson j = new AjaxJson();
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(countname);
		if (m.find()) {
			return false;
		}
		return true;
	}

}