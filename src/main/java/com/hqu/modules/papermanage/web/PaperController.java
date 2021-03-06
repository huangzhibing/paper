/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.papermanage.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.jeeplus.common.utils.FileUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.security.SystemAuthorizingRealm;
import com.jeeplus.modules.sys.utils.UserUtils;
import com.jeeplus.modules.sys.web.FileController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.hqu.modules.basedata.studentmanage.entity.StudentManage;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.papereview.order.entity.Order;
import com.hqu.modules.papereview.order.service.OrderService;
import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.papermanage.entity.PaperImport;
import com.hqu.modules.papermanage.service.PaperService;

/**
 * 论文管理Controller
 * @author dongqida
 * @version 2018-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/papermanage/paper")
public class PaperController extends BaseController {

	@Autowired
	private PaperService paperService;
	@Autowired
	private OrderService orderService;
	@ModelAttribute
	public Paper get(@RequestParam(required=false) String id) {
		Paper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = paperService.get(id);
		}
		if (entity == null){
			entity = new Paper();
		}
		return entity;
	}
	
	/**
	 * 论文管理列表页面
	 */
	@RequiresPermissions("papermanage:paper:list")
	@RequestMapping(value = {"list", ""})
	public String list(String payment,Paper paper, Model model) {
		model.addAttribute("paper", paper);
		model.addAttribute("payment",payment);
		return "modules/papermanage/paperList";
	}
	
		/**
	 * 论文管理列表数据
		 * @throws ParseException 
	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String payment,Paper paper, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
//		if(paper.getBeginUpdateDate() == null && paper.getEndUpdateDate() == null) {
//			Calendar calender = Calendar.getInstance();
//			Date date = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String sdate = sdf.format(date);
//			calender.setTime(sdf.parse(sdate+" 00:00:00"));
//			calender.set(Calendar.MONTH, -1);
//			paper.setBeginUpdateDate(calender.getTime());
//			paper.setEndUpdateDate(sdf.parse(sdate+" 23:59:59"));
//		}
		model.addAttribute("payment",payment);
		paper.setNo(UserUtils.getUser().getNo());
		Page<Paper> page = paperService.findPage(new Page<Paper>(request, response), paper); 
		for(int i=0;i<page.getList().size();i++) {
			if(StringUtils.isNotBlank(page.getList().get(i).getLWWJ())) {
				String name = page.getList().get(i).getLWWJ();
				name = name.substring(name.lastIndexOf("/")+1, name.length());
				page.getList().get(i).setName(name);
			}
		}
		
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑论文管理表单页面
	 */
	@RequiresPermissions(value={"papermanage:paper:view","papermanage:paper:add","papermanage:paper:edit"},logical=Logical.OR)
	@RequestMapping(value = "form/{mode}")
	public String form(@PathVariable String mode, Paper paper, Model model) {
		model.addAttribute("paper", paper);
		model.addAttribute("mode", mode);
		if(StringUtils.isBlank(paper.getId())) {
			String paperId = System.currentTimeMillis()+"00000";
			paper.setLWBH(paperId);
			//paper.setLWZTDM("a");
		}
		return "modules/papermanage/paperForm";
	}

	/**
	 * 保存论文管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"papermanage:paper:add","papermanage:paper:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Paper paper, Model model,HttpServletRequest req) throws Exception{
		AjaxJson j = new AjaxJson();
		
		if(paper.getLWWJ() != null && !paper.getLWWJ().equals("")) {
			String tempName = paper.getLWWJ().substring(paper.getLWWJ().lastIndexOf("/")+1,paper.getLWWJ().lastIndexOf("."));
			if(!paper.getLWMC().equals(tempName)) {
				FileController fileController = new FileController();
				fileController.delFile(paper.getLWWJ());
				System.out.println(tempName+"-++-");
				j.setSuccess(false);
				j.setMsg("文件名与论文名称不一致，请重新上传");
				return j;
			}
		}
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
		if(paper.getLWZTDM()==null||"".equals(paper.getLWZTDM())) {
			paper.setLWZTDM("a");
		}
		System.out.println(paper+"-=-=-=");
		paperService.save(paper);//保存
		j.setSuccess(true);
		j.setMsg("保存论文管理成功");
		return j;
	}
	
	/**
	 * 删除论文管理
	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Paper paper) {
		AjaxJson j = new AjaxJson();
		paperService.delete(paper);
		j.setMsg("删除论文管理成功");
		return j;
	}
	
	/**
	 * 批量删除论文管理
	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			paperService.delete(paperService.get(id));
		}
		j.setMsg("删除论文管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(Paper paper, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "论文管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Paper> page = paperService.findPage(new Page<Paper>(request, response, -1), paper);
    		new ExportExcel("论文管理", Paper.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出论文管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PaperImport> list = ei.getDataList(PaperImport.class);
			String preId = System.currentTimeMillis()+"";
			for (PaperImport paperImport : list){
				try{
					Paper paper = new Paper();
					String paperId = preId+String.format("%05d", successNum);
					paper.setLWBH(paperId);
					paper.setBeginCreateDate(paperImport.getBeginCreateDate());
					paper.setCreateBy(paperImport.getCreateBy());
					paper.setCreateDate(paperImport.getCreateDate());
					paper.setCurrentUser(paperImport.getCurrentUser());
					paper.setDataScope(paperImport.getDataScope());
					paper.setDelFlag(paperImport.getDelFlag());
					paper.setEndCreateDate(paperImport.getEndCreateDate());
					paper.setIdType("UUID");
					paper.setIsNewRecord(false);
					paper.setLWLXDM(paperImport.getLWLXDM());
					paper.setLWMC(paperImport.getLWMC());
					paper.setLWZTDM(paperImport.getLWZTDM());
					paper.setPage(paperImport.getPage());
					paper.setUpdateBy(paperImport.getUpdateBy());
					paper.setId("");
					paper.setUpdateDate(paperImport.getUpdateDate());
					StudentManage XSXH = new StudentManage();
					XSXH.setXsxh(paperImport.getXSXH());
					paper.setXSXH(XSXH);
					paper.setLwzy(paperImport.getLwzy());
					paperService.save(paper);
					successNum++;
				}catch(ConstraintViolationException ex){
					ex.printStackTrace();
					failureNum++;
				}catch (Exception ex) {
					ex.printStackTrace();
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条论文管理记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条论文管理记录"+failureMsg);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("导入论文管理失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入论文管理数据模板
	 */
	@ResponseBody
	@RequiresPermissions("papermanage:paper:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "论文管理数据导入模板.xlsx";
    		List<Paper> list = Lists.newArrayList(); 
    		new ExportExcel("论文管理数据", Paper.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }
	/**
	 * 批量上传
	 * 董奇达
	 * @param fileValues
	 * @param readonly
	 * @param uploadPath
	 * @param type
	 * @param fileNumLimit
	 * @param fileSizeLimit
	 * @param allowedExtensions
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("user")
	@RequestMapping(value = "fileUploadModify")
	public String fileUploadModify(String fileSizeLimit, String readonly, String uploadPath, String type,String ids,Model model) {
		
		
		String mimeTypes = "";
		
		String allowedExtensions = "doc,docx,pdf";
		mimeTypes=".doc,.docx,.pdf";
		model.addAttribute("uploadPath", uploadPath+"/"+ids);
		model.addAttribute("type", type);
		model.addAttribute("fileSizeLimit", fileSizeLimit);
		model.addAttribute("allowedExtensions", allowedExtensions);
		model.addAttribute("mimeTypes", mimeTypes);
		model.addAttribute("readonly", "true".equals(readonly)?true:false);
		model.addAttribute("ids", ids);
		return "modules/common/fileUpload_modify";
	}
	@RequestMapping(value = "webupload/upload")
	@ResponseBody
	public AjaxJson webupload( HttpServletRequest request, HttpServletResponse response,MultipartFile file) throws IllegalStateException, IOException {
        AjaxJson j = new AjaxJson();
        String path = request.getParameter("uploadPath");
        String filename = file.getOriginalFilename();
        String realName = filename.substring(0, filename.indexOf("."));
        String ids = path.substring(path.lastIndexOf("/")+1, path.length());
        if(!ids.contains(realName)) {
        	j.setSuccess(false);
			j.setMsg("文件名不一致");System.out.println("--++--++");
			j.put("id", "");
			j.put("url", "名字不相符请重新上传");
			return j;
        }
	    User currentUser = UserUtils.getUser();
		String uploadPath = path.substring(0,path.lastIndexOf("/"));
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
		SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) UserUtils.getPrincipal();
		String fileUrl = Global.getAttachmentUrl()+uploadPath+"/"+year+"/"+month+"/";
		String fileDir = Global.getAttachmentDir()+uploadPath+"/"+year+"/"+month+"/";
		// 判断文件是否为空
		if (!file.isEmpty()) {
			// 文件保存路径
			// 转存文件
			FileUtils.createDirectory(fileDir);
			String name = file.getOriginalFilename();
			String filePath = fileDir +  name;
			File newFile = FileUtils.getAvailableFile(filePath,0);
			file.transferTo(newFile);
			j.put("id", newFile.getAbsolutePath());
			j.put("url", fileUrl+ newFile.getName());
			System.out.println("->->");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lwwj", fileUrl+ newFile.getName());
			map.put("lwmc", newFile.getName().substring(0, newFile.getName().lastIndexOf(".")));
			System.out.println(fileUrl+"-");
			int t=paperService.updatePaperPath(map);
			System.out.println(t+"-><-");
		}
		
		return j;
	}

	/**
	 * 缴费
	 */
	@ResponseBody
	@RequestMapping(value = "payment")
	public AjaxJson payment(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		Random random=new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Order order=new Order();
		//完善订单信息
		String num = Integer.toString(random.nextInt(10000));
		order.setDdh(sdf.format(new Date()) + num);
		order.setXdrq(new Date());
		
		
		order.setDdztdm("a");
		
		//生成订单
		orderService.save(order);
		//为每篇论文分配专家
		for(String i : idArray){
			Paper paper=paperService.get(i);
			//修改状态
			paper.setLWZTDM("b");
			paper.setJfddh(order.getDdh());
			paperService.save(paper);
		}
		j.setMsg("论文缴费成功");
		return j;
	}
}