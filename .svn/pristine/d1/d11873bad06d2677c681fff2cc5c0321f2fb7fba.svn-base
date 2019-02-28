/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.feedback_count.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.hqu.modules.basedata.feedback.entity.Feedback;
import com.hqu.modules.basedata.feedback.service.FeedbackService;
import com.hqu.modules.basedata.university.entity.University;
import com.hqu.modules.basedata.university.service.UniversityService;
import com.hqu.modules.common.service.CommonService;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.service.ExpertService;
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
import com.hqu.modules.feedback_count.entity.Feedback_count;
import com.hqu.modules.feedback_count.service.Feedback_countService;

/**
 * 反馈统计Controller
 * @author wdz
 * @version 2018-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/feedback_count/feedback_count")
public class Feedback_countController extends BaseController {
	@Autowired
	ExpertService expertService;
	@Autowired
	UniversityService universityService;
	@Autowired
	FeedbackService feedbackerviceService;
	@RequestMapping(value = {"index",""})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/modules/feedback_count/feedback_countForm";
	}
	@ResponseBody
	@RequestMapping("bar")
	public GsonOption getOption(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate){

		List<University> university=universityService.selectAll_University();
		int FkZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","0");
		int FkJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","1");
		int Fknum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","2");
		int HfZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","0");
		int HfJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","1");
		int Hfnum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","2");
		GsonOption option = new GsonOption();
		option.title().text("反馈统计");
		option.tooltip().trigger(Trigger.axis);
		option.legend("反馈个数", "回复个数");
		option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.pie, Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
		option.calculable(true);
		option.xAxis(new CategoryAxis().data("咨询","建议","反馈"));
		option.yAxis(new ValueAxis());

		Bar bar = new Bar("反馈个数");
		bar.data(FkZxNum,FkJyNum,Fknum);
		bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
		bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

		Bar bar2 = new Bar("回复个数");
		bar2.data(HfZxNum,HfJyNum,Hfnum);
		bar2.markPoint().data(new PointData("年最高", 182).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2).xAxis(11).yAxis(3));
		bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

		option.series(bar, bar2);
		return option;
	}
	@ResponseBody
	@RequestMapping("pie")
	public GsonOption getOption1(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate){
		GsonOption option = new GsonOption();
		int FkZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","0");
		int FkJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","1");
		int Fknum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","2");
		int HfZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","0");
		int HfJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","1");
		int Hfnum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","2");
		//时间轴
		option.timeline().data("2018-01-01", "2018-02-01", "2018-03-01", "2018-04-01", "2018-05-01",
				new LineData("2018-06-01", "emptyStart6", 8), "2018-07-01", "2018-08-01", "2018-09-01", "2018-10-01",
				"2018-11-01", new LineData("2018-12-01", "star6", 8));
		option.timeline().autoPlay(true);
		//timeline变态的地方在于多个Option
		Option basic = new Option();
		basic.title().text("反馈回复占比变化");
		basic.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
		basic.legend().data("咨询","回复咨询","建议","回复建议","反馈","回复反馈");
		basic.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage, new MagicType(Magic.pie, Magic.funnel)
				.option(new MagicType.Option().funnel(
						new Funnel().x("25%").width("50%").funnelAlign(X.left).max(1548))));

		int idx = 1;
		Pie pie1=new Pie().name("反馈回复占比").data(
				new PieData("咨询", FkZxNum),
				new PieData("回复咨询", HfZxNum),
				new PieData("建议", FkJyNum),
				new PieData("回复建议", HfJyNum),
				new PieData("反馈", Fknum),
				new PieData("回复反馈", Hfnum));
		Pie pie = pie1.center("50%", "45%").radius("50%");
		pie.label().normal().show(true).formatter("{b}{c}({d}%)");
		basic.series(pie);
		//加入
		option.options(basic);
		//构造11个数据
		Option[] os = new Option[11];
		for (int i = 0; i < os.length; i++) {
			os[i] = new Option().series(pie1);
		}
		option.options(os);
		return option;
	}

	/**
	 * 获取饼图数据
	 *
//	 * @param idx
	 * @return
	 */
	@ResponseBody
	@RequestMapping("line")
	public GsonOption getOption2(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate){
		int FkZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","0");
		int FkJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","1");
		int Fknum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","fksj","2");
		int HfZxNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","0");
		int HfJyNum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","1");
		int Hfnum=feedbackerviceService.getNumByDate(beginDate,endDate,"t_feedback","hfsj","2");
		GsonOption option = new GsonOption();
		option.tooltip().trigger(Trigger.axis);
		option.legend("反馈个数", "回复个数");
		option.toolbox().show(true);
		option.calculable(true);
		option.xAxis(new CategoryAxis().boundaryGap(false).data("咨询","建议","反馈"));
		option.yAxis(new ValueAxis());
		option.series(new Line().smooth(true).name("反馈个数").stack("总量").symbol(Symbol.none).data(FkZxNum,FkJyNum,Fknum));
		//实现不了js的这个效果
		//line.itemStyle.normal.areaStyle = new AreaStyle();
		option.series(new Line().smooth(true).name("回复个数").stack("总量").symbol("image://http://echarts.baidu.com/doc/asset/ico/favicon.png").symbolSize(8).data(HfZxNum,HfJyNum,Hfnum));

       /* line = new Line();
        line.name = "邮件营销";
        line.stack = "总量";
        line.symbol = Symbol.none;
        line.smooth = true;
        //实现不了js的这个效果
        //line.itemStyle.normal.areaStyle = new AreaStyle();
        line.addData(120, 132, 301, 134,new LineData(90,Symbol.droplet,5),230,210);
        option.series.add(line);

        line = new Line();
        line.name = "邮件营销";
        line.stack = "总量";
        line.symbol = Symbol.none;
        line.smooth = true;
        //实现不了js的这个效果
        //line.itemStyle.normal.areaStyle = new AreaStyle();
        line.addData(120, 132, 301, 134,new LineData(90,Symbol.droplet,5),230,210);
        option.series.add(line);*/

		return option;
	}
}