package com.hqu.modules.paperreceive.myaccount.web;

import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.service.ExpertService;
import com.hqu.modules.mypanel.expertinfomanage.entity.BankKhh;
import com.hqu.modules.mypanel.expertinfomanage.mapper.BankKhhMapper;
import com.hqu.modules.mypanel.expertinfomanage.service.BankKhhService;
import com.hqu.modules.papereview.order.entity.Order;
import com.hqu.modules.papereview.order.service.OrderService;
import com.hqu.modules.papermanage.entity.Paper;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.sys.utils.UserUtils;
import org.apache.http.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 提现功能
 * hzb
 */
@Controller
@RequestMapping(value = "${adminPath}/myaccount/myAccount")
public class myAccountController extends BaseController{

    @Autowired
    ExpertService expertService;
    @Autowired
    OrderService orderService;
    @Autowired
    BankKhhService bankKhhMapper;

    @ModelAttribute
    public Expert get(@RequestParam(required=false) String id) {
        Expert entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = expertService.get(id);
        }
        if (entity == null){
            entity = new Expert();
        }
        return entity;
    }
    @RequiresPermissions("myaccount:myAccount:list")
    @RequestMapping(value = "")
    public String list(Model model){
        Expert expert = new Expert();
        String loginName = UserUtils.getUser().getLoginName();
        if("admin".equals(loginName)){
            expert.setYHZH("admin");
            expert.setZJXM("admin");
            expert.setYE(999999.9);
        }else {
            expert.setYHZH(loginName);
            expert = expertService.findList(expert).get(0);
        }

        model.addAttribute("expert",expert);
        return "paperreceive/myaccount/myAccount";
    }

    @ResponseBody
    @RequestMapping(value = "save")
    public AjaxJson save(String txje){
        AjaxJson ajaxJson = new AjaxJson();
        Expert expert = new Expert();
        expert.setYHZH(UserUtils.getUser().getLoginName());
        expert = expertService.findList(expert).get(0);

        if(expert.getYE() == 0){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("用户余额为零");
        }
        else if(expert.getYE() < Double.parseDouble(txje)){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("用户余额不足");
        }else {
            //修改用户的余额
            Double nowMoney = expert.getYE()-Double.parseDouble(txje);
            expert.setYE(nowMoney);
            //生成一条提现订单
            Order order = new Order();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Random random = new Random();
            String num = Integer.toString(random.nextInt(10000));
            order.setDdh(sdf.format(new Date()) + num);
            order.setXdrq(new Date());
            order.setDdlxdm("1");
            order.setZj(Double.parseDouble(txje));
            order.setKhxm(expert.getZJXM());
            order.setYddh(expert.getYDDH());
            order.setDdztdm("a");
            orderService.save(order);
            expertService.save(expert);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("提现发起成功,预计会在两小时后到账");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "bankInfoForm")
    public String bankInfoForm(String flag,Model model){
        String loginName = UserUtils.getUser().getLoginName();
        Expert expert = expertService.get(loginName);
        model.addAttribute("flag",flag);
        model.addAttribute("expert",expert);
        return "paperreceive/myaccount/bankInfoModify";
    }

    @ResponseBody
    @RequestMapping(value = "saveBankInfo")
    public AjaxJson saveBankInfo(Expert expert){
        AjaxJson ajaxJson = new AjaxJson();
        expertService.saveBankInfo(expert);
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("修改账户信息成功");

        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "bankData")
    public Map<String,Object> bankData(BankKhh bankKhh, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        Page<BankKhh> page = bankKhhMapper.findPage(new Page<BankKhh>(httpRequest,httpResponse),bankKhh);
        return getBootstrapData(page);
    }
}
