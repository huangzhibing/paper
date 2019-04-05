package com.hqu.modules.paperreceive.daishenpaper.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.service.ExpertService;
import com.hqu.modules.papereview.order.entity.Order;
import com.hqu.modules.papereview.order.service.OrderService;
import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.papermanage.service.PaperService;
import com.hqu.modules.paperreceive.daishenpaper.service.DaishenPaperService;
import com.jeeplus.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;



/**
 * 待审论文模块
 * hzb
 */

@Controller
@RequestMapping(value = "${adminPath}/daishenpaper/daishenPaper")
public class DaishenPaperController extends BaseController{

    @Autowired
    DaishenPaperService daishenPaperService;
    @Autowired
    PaperService paperService;
    @Autowired
    ExpertService expertService;
    @Autowired
    OrderService orderService;

    /**
     * 评审记录列表页面
     */
    @RequiresPermissions("daishenpaper:daishenPaper:list")
    @RequestMapping(value = "")
    public String list(Paper paper, Model model) {
        model.addAttribute("paper", paper);
        return "paperreceive/daishenpaper/daishenPaperList";
    }

    /**
     * 评审记录列表数据
     */
    @ResponseBody
    @RequiresPermissions("daishenpaper:daishenPaper:list")
    @RequestMapping(value = "data")
    public Map<String, Object> data(Paper paper, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Paper> page = daishenPaperService.findPage(new Page<Paper>(request, response), paper);

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
    @RequestMapping(value = "form/{mode}")
    public String form(@PathVariable String mode, Paper paper, Model model) {
        if(StringUtils.isNotBlank(paper.getId())) {
            Paper paper1 = paperService.get(paper.getId());
            model.addAttribute("paper", paper1);
        }else {
            model.addAttribute("paper", paper);
        }
        model.addAttribute("paperId",paper.getId());
        model.addAttribute("mode", mode);

        return "paperreceive/daishenpaper/daishenPaperForm";
    }

    /**
     * 保存评审记录
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public AjaxJson save(String jieshou,String jushou,String id,Paper paper, Model model) throws Exception{
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
        //对指派的论文进行接收和拒收操作
        Paper zhipaipaper = paperService.get(paper.getId());
        if("true".equals(jieshou)) {
            zhipaipaper = paperService.get(id);
            zhipaipaper.setLWZTDM("e");
            j.setSuccess(true);
            j.setMsg("论文接收成功");
        }else {
            zhipaipaper.setLWZTDM("d");
            Order order = new Order();
            order.setLw(zhipaipaper);
            String userID =  UserUtils.getUser().getLoginName();
            Expert expert = expertService.get(userID);
            order.setPszj(expert);
            List<Order> orderList = orderService.findList(order);
            orderList.get(0).setDdztdm("h");
            orderService.save(orderList.get(0));
            j.setSuccess(true);
            j.setMsg("论文拒收成功");
        }

        paperService.save(zhipaipaper);
        return j;
    }
}
