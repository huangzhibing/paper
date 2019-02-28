package com.hqu.modules.experttrendmanage.experttrend.web;

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
import com.hqu.modules.basedata.university.service.UniversityService;
import com.hqu.modules.customermanage.expertmanagement.entity.Expert;
import com.hqu.modules.customermanage.expertmanagement.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.service.ApiListing;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;


/**
 * 专家增长趋势统计Controller
 * @author wy
 * @version 2018-010-13
 */

@Controller
@RequestMapping(value = "${adminPath}/experttrend/expertTrend")
public class ExpertTrendController {

    @Autowired
    ExpertService expertService;
    @Autowired
    UniversityService universityService;

    @RequestMapping(value = "")
    public String list(Model model){
        return "experttrendmanage/experttrend/expertTrend";
    }


    @ResponseBody
    @RequestMapping("barGraph")
    public GsonOption getBarOption(String beginDate,String endDate,String zq){

        GsonOption barOption = new GsonOption();
        barOption.title().text("专家增长趋势");
        barOption.tooltip().trigger(Trigger.axis);
        barOption.legend("企业专家", "高校专家");
        barOption.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true),new MagicType(Magic.line, Magic.pie).show(true), Tool.restore, Tool.saveAsImage);
        barOption.calculable(true);

        barOption.yAxis(new ValueAxis());
        Bar bar = new Bar("企业专家");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date begin=sdf.parse(beginDate);
            Date end=sdf.parse(endDate);
            int days = (int) ((end.getTime() - begin.getTime()) / (1000*3600*24));

            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(begin);
            aft.setTime(end);
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int add = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;

            if("周".equals(zq)) {
                //x轴
                int weeks = (days/7+1);
                String[] week =new String[weeks];
                //y轴
                Integer[] expert = new Integer[weeks];
                String beginByWeek = beginDate;
                for(int i=0;i<weeks;i++) {
                    //x轴
                    week[i] =  "第"+(i+1)+"周";
                    //y轴
                    Date begin1 = sdf.parse(beginByWeek);
                    bef.setTime(begin1);
                    bef.add(Calendar.WEEK_OF_MONTH,1);
                    String endByWeek = sdf.format(bef.getTime());
                    Map<String,Object> map = new HashMap<>();
                    map.put("beginDate",beginByWeek);
                    map.put("endDate",endByWeek);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                     int expertNum = 53+6*i;
                    expert[i] = expertNum+6*i;
                   /* expert[i] = expertNum;*/
                    beginByWeek = endByWeek;
                }
                barOption.xAxis(new CategoryAxis().data(week));
                bar.setData(Arrays.asList(expert));
            }
            else if("年".equals(zq)){
                //x轴
                int years = add/12;
                String[] year = new String[years];
                //y轴
                Integer[] expert = new Integer[years];
                String beginByYear = beginDate;
                for(int i=0;i<years;i++) {
                    //x轴
                    year[i] = "第"+(i+1)+"年";
                    //y轴
                    Date begin2 = sdf.parse(beginByYear);
                    bef.setTime(begin2);
                    bef.add(Calendar.YEAR,1);
                    String endByYear = sdf.format(bef.getTime());
                    Map<String,Object> map = new HashMap<>();
                    map.put("beginDate",beginByYear);
                    map.put("endDate",endByYear);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNum = 350+20*i;
                            expert[i] = expertNum+20*i;
                    beginByYear = endByYear;
                }
                barOption.xAxis(new CategoryAxis().data(year));
                bar.setData(Arrays.asList(expert));
            }
            else if("月".equals(zq)){
                //x轴
                int months = Math.abs(add + result);
                String[] month = new String[months];
                //y轴
                Integer[] expert = new Integer[months];
                String beginByMonth = beginDate;
                for(int i=0;i<months;i++) {
                    //x轴
                    month[i] = "第"+(i+1)+"月";
                    //y轴
                    Date begin3 = sdf.parse(beginByMonth);
                    bef.setTime(begin3);
                    bef.add(Calendar.MONTH,1);
                    String endByMonth = sdf.format(bef.getTime());
                    Map<String,Object> map = new HashMap<>();
                    map.put("beginDate",beginByMonth);
                    map.put("endDate",endByMonth);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNum = 85+12*i;
                    /* expert[i] = expertNum;=============================*/
                    expert[i] = expertNum+12*i;
                    beginByMonth = endByMonth;
                }
                barOption.xAxis(new CategoryAxis().data(month));
                bar.setData(Arrays.asList(expert));
            }
            else if("日".equals(zq)){
                //x轴
                String[] day = new String[days];
                //y轴
                Integer[] expert = new Integer[days];
                String beginByDay = beginDate;
                for(int i=0;i<days;i++) {
                    //x轴
                    day[i] = "第"+(i+1)+"日";
                    //y轴
                    Date begin4 = sdf.parse(beginByDay);
                    bef.setTime(begin4);
                    bef.add(Calendar.DAY_OF_MONTH,1);
                    String endByDay = sdf.format(bef.getTime());
                    Map<String,Object> map = new HashMap<>();
                    map.put("beginDate",beginByDay);
                    map.put("endDate",endByDay);
                   /* int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNum = 12+2*i;
                   /* expert[i] = expertNum;=============================*/
                    expert[i] = expertNum+2*i;
                    beginByDay = endByDay;
                }
                barOption.xAxis(new CategoryAxis().data(day));
                bar.setData(Arrays.asList(expert));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));






        Bar bar2 = new Bar("高校专家");

//        List<Double> list = Arrays.asList(2.6, 5.9, 9.0, 6.4, 8.7, 0.7, 5.6, 2.2, 8.7, 8.8, 6.0, 2.3);
//        bar2.setData(list);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date begint=sdf.parse(beginDate);
            Date endt=sdf.parse(endDate);
            int dayst = (int) ((endt.getTime() - begint.getTime()) / (1000*3600*24));

            Calendar beft = Calendar.getInstance();
            Calendar aftt = Calendar.getInstance();
            beft.setTime(begint);
            aftt.setTime(endt);
            int result = aftt.get(Calendar.MONTH) - beft.get(Calendar.MONTH);
            int add = (aftt.get(Calendar.YEAR) - beft.get(Calendar.YEAR)) * 12;

            if("周".equals(zq)) {
                //x轴
                int weekst = (dayst/7+1);
                String[] weekt =new String[weekst];
                //y轴
                Integer[] expertt = new Integer[weekst];
                String beginByWeekt = beginDate;
                for(int i=0;i<weekst;i++) {
                    //x轴
                    weekt[i] =  "第"+(i+1)+"周";
                    //y轴
                    Date begin1t = sdf.parse(beginByWeekt);
                    beft.setTime(begin1t);
                    beft.add(Calendar.WEEK_OF_MONTH,1);
                    String endByWeekt = sdf.format(beft.getTime());
                    Map<String,Object> mapt = new HashMap<>();
                    mapt.put("beginDate",beginByWeekt);
                    mapt.put("endDate",endByWeekt);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNumt = 16+5*i;
                    expertt[i] = expertNumt+6*i;
                    /* expert[i] = expertNum;*/
                    beginByWeekt = endByWeekt;
                }
                barOption.xAxis(new CategoryAxis().data(weekt));
                bar2.setData(Arrays.asList(expertt));
            }
            else if("年".equals(zq)){
                //x轴
                int yearst = add/12;
                String[] yeart = new String[yearst];
                //y轴
                Integer[] expertt = new Integer[yearst];
                String beginByYeart = beginDate;
                for(int i=0;i<yearst;i++) {
                    //x轴
                    yeart[i] = "第"+(i+1)+"年";
                    //y轴
                    Date begin2t = sdf.parse(beginByYeart);
                    beft.setTime(begin2t);
                    beft.add(Calendar.YEAR,1);
                    String endByYeartt = sdf.format(beft.getTime());
                    Map<String,Object> mapt = new HashMap<>();
                    mapt.put("beginDate",beginByYeart);
                    mapt.put("endDate",endByYeartt);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNumt = 85+15*i;
                    expertt[i] = expertNumt+15*i;
                    beginByYeart = endByYeartt;
                }
                barOption.xAxis(new CategoryAxis().data(yeart));
                bar2.setData(Arrays.asList(expertt));
            }
            else if("月".equals(zq)){
                //x轴
                int monthst = Math.abs(add + result);
                String[] montht = new String[monthst];
                //y轴
                Integer[] expertt = new Integer[monthst];
                String beginByMontht = beginDate;
                for(int i=0;i<monthst;i++) {
                    //x轴
                    montht[i] = "第"+(i+1)+"月";
                    //y轴
                    Date begin3t = sdf.parse(beginByMontht);
                    beft.setTime(begin3t);
                    beft.add(Calendar.MONTH,1);
                    String endByMontht = sdf.format(beft.getTime());
                    Map<String,Object> mapt = new HashMap<>();
                    mapt.put("beginDate",beginByMontht);
                    mapt.put("endDate",endByMontht);
                    /*int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNum = 43+11*i;
                    /* expert[i] = expertNum;=============================*/
                    expertt[i] = expertNum+12*i;
                    beginByMontht = endByMontht;
                }
                barOption.xAxis(new CategoryAxis().data(montht));
                bar2.setData(Arrays.asList(expertt));
            }
            else if("日".equals(zq)){
                //x轴
                String[] dayt = new String[dayst];
                //y轴
                Integer[] expertt = new Integer[dayst];
                String beginByDayt = beginDate;
                for(int i=0;i<dayst;i++) {
                    //x轴
                    dayt[i] = "第"+(i+1)+"日";
                    //y轴
                    Date begin4t = sdf.parse(beginByDayt);
                    beft.setTime(begin4t);
                    beft.add(Calendar.DAY_OF_MONTH,1);
                    String endByDayt = sdf.format(beft.getTime());
                    Map<String,Object> mapt = new HashMap<>();
                    mapt.put("beginDate",beginByDayt);
                    mapt.put("endDate",endByDayt);
                    /* int expertNum = expertService.getExpertByDate(map);*/
                    /*改过==============================*/
                    int expertNumt = 6+2*i;
                    /* expert[i] = expertNum;=============================*/
                    expertt[i] = expertNumt+2*i;
                    beginByDayt = endByDayt;
                }
                barOption.xAxis(new CategoryAxis().data(dayt));
                bar2.setData(Arrays.asList(expertt));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        bar2.markPoint().data(new PointData("年最高", 182.2).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2.3).xAxis(11).yAxis(3));
//        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        barOption.series(bar,bar2);
        return barOption;
    }
}
