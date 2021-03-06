/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */

package com.hqu.modules.numericalstatement.web;

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
import com.hqu.modules.basedata.university.entity.University;
import com.hqu.modules.basedata.university.service.UniversityService;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author jeeplus
 */
@Controller
@RequestMapping(value = "${adminPath}/numericalstatement/expert_count")
public class ExpertCount {
    @Autowired
    ExpertService expertService;
    @Autowired
    UniversityService universityService;


    @RequestMapping(value = {"","list"})
    public String list(Model model){
        return "/modules/numericalstatement/expert_countForm";
    }

    @ResponseBody
    @RequestMapping("data")
    public List<EC> data(String zjzt){
        return expertService.getExpertByGX(zjzt);
    }

    @ResponseBody
    @RequestMapping("bar")
    public GsonOption getOption(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate,@RequestParam(value = "zjlxdm",required = false)String zjlxdm,@RequestParam(value = "zjztdm",required = false)String zjztdm,@RequestParam(value = "xbdm",required = false)String xbdm,@RequestParam(value = "flag",required = false)String flag){
        List<University> university=universityService.selectAll_University();
        GsonOption option = new GsonOption();
        option.title().text("平台专家数量分布情况");
        option.tooltip().trigger(Trigger.axis);
        option.legend("已激活专家数", "专家总数");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.pie, Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        String[] university_name=new String[university.size()];
        int[] qy_expert_number=new int[university.size()];
        int[] gx_expert_number=new int[university.size()];
        int i=0;
        Bar bar = new Bar("已激活专家数");
        Bar bar2 = new Bar("专家总数");
        for (University university1:university) {
            university_name[i]=university1.getGxmc();
            if(flag .equals("1") ){
                qy_expert_number[i]=expertService.selectOneTypeExpert(university1.getGxdm(),"1",beginDate,endDate,zjztdm,xbdm);
                gx_expert_number[i]=expertService.selectOneTypeExpert(university1.getGxdm(),"0",beginDate,endDate,zjztdm,xbdm);
            }
            else {
                qy_expert_number[i]=expertService.selectOneType_Expert(university1.getGxdm(),"1");
                gx_expert_number[i]=expertService.selectOneType_Expert(university1.getGxdm(),"0");
            }
            bar.data(qy_expert_number[i]);
            bar2.data(gx_expert_number[i]);
            i++;
    }
        option.xAxis(new CategoryAxis().data(university_name));
        option.yAxis(new ValueAxis());
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        bar2.markPoint().data(new PointData("年最高", 182).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2).xAxis(11).yAxis(3));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        option.series(bar, bar2);
        return option;
    }
    @ResponseBody
    @RequestMapping("pie")
    public GsonOption getOption1(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate,@RequestParam(value = "zjlxdm",required = false)String zjlxdm,@RequestParam(value = "zjztdm",required = false)String zjztdm,@RequestParam(value = "xbdm",required = false)String xbdm,@RequestParam(value = "flag",required = false)String flag){
        GsonOption option = new GsonOption();
        List<University> university=universityService.selectAll_University();
        //时间轴
        option.timeline().data("2013-01-01", "2013-02-01", "2013-03-01", "2013-04-01", "2013-05-01",
                new LineData("2013-06-01", "emptyStart6", 8), "2013-07-01", "2013-08-01", "2013-09-01", "2013-10-01",
                "2013-11-01", new LineData("2013-12-01", "star6", 8));
        option.timeline().autoPlay(true);
        //timeline变态的地方在于多个Option
        Option basic = new Option();
        basic.title().text("各高校专家占比变化");
        basic.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
        String[] university_name=new String[university.size()];
        int j=0;
        for (University university1:university) {
            university_name[j] = university1.getGxmc();
            j++;
        }
        basic.legend().data(university_name);
        basic.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage, new MagicType(Magic.pie, Magic.funnel)
                .option(new MagicType.Option().funnel(
                        new Funnel().x("25%").width("50%").funnelAlign(X.left).max(1548))));

        int idx = 1;
        Pie pie = getPie(idx++).center("50%", "45%").radius("50%");
        pie.label().normal().show(true).formatter("{b}{c}({d}%)");
        basic.series(pie);
        //加入
        option.options(basic);
        //构造11个数据

        Option[] os = new Option[11];
        for (int i = 0; i < os.length; i++) {
            os[i] = new Option().series(getPie(idx++));
        }
        option.options(os);
        return option;
    }

    /**
     * 获取饼图数据
     *
     * @param idx
     * @return
     */
    public Pie getPie(int idx) {
        List<University> university=universityService.selectAll_University();
        PieData[] pieDatas=new PieData[university.size()*10];
        Pie pie=new Pie("专家个数");
        int i=0;
        for (University university1:university) {
            PieData qy_pieData=new PieData(university1.getGxmc()+"企业专家",expertService.selectOneType_Expert(university1.getGxdm(),"1"));
            pieDatas[i]=qy_pieData;
            PieData gx_pieData=new PieData(university1.getGxmc()+"高校专家",expertService.selectOneType_Expert(university1.getGxdm(),"0"));
            pieDatas[++i]=gx_pieData;
            i++;
        }
        return pie.data(pieDatas);
    }
    @ResponseBody
    @RequestMapping("line")
    public GsonOption getOption2(@RequestParam(value = "beginDate",required = false)String beginDate,@RequestParam(value = "endDate",required = false)String endDate,@RequestParam(value = "zjlxdm",required = false)String zjlxdm,@RequestParam(value = "zjztdm",required = false)String zjztdm,@RequestParam(value = "xbdm",required = false)String xbdm,@RequestParam(value = "flag",required = false)String flag){
        GsonOption option = new GsonOption();
        List<University> university=universityService.selectAll_University();
        option.tooltip().trigger(Trigger.axis);
        option.legend("已激活专家数", "专家总数");
        option.toolbox().show(true);
        option.calculable(true);
        String[] university_name=new String[university.size()];
        int[] qy_expert_number=new int[university.size()];
        int[] gx_expert_number=new int[university.size()];
        int i=0;
        Line line = new Line("已激活专家数");
        Line line1 = new Line("专家总数");
        for (University university1:university) {
            university_name[i]=university1.getGxmc();
            if(flag .equals("1") ){
                qy_expert_number[i]=expertService.selectOneTypeExpert(university1.getGxdm(),"1",beginDate,endDate,zjztdm,xbdm);
                gx_expert_number[i]=expertService.selectOneTypeExpert(university1.getGxdm(),"0",beginDate,endDate,zjztdm,xbdm);
            }
            else {
                qy_expert_number[i]=expertService.selectOneType_Expert(university1.getGxdm(),"1");
                gx_expert_number[i]=expertService.selectOneType_Expert(university1.getGxdm(),"0");
            }
            line.data(qy_expert_number[i]);
            line1.data(gx_expert_number[i]);
            i++;
        }
        option.xAxis(new CategoryAxis().boundaryGap(false).data(university_name));
        option.yAxis(new ValueAxis());
        option.series(line,line1);
        //实现不了js的这个效果
        //line.itemStyle.normal.areaStyle = new AreaStyle();

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
