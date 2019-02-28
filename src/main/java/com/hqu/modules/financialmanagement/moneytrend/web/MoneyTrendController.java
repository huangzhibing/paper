package com.hqu.modules.financialmanagement.moneytrend.web;

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
import com.hqu.modules.papereview.order.entity.Order;
import com.hqu.modules.papereview.order.service.OrderService;
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
 * 进账趋势统计Controller
 * @author hzb
 * @version 2018-09-24
 */

@Controller
@RequestMapping(value = "${adminPath}/moneytrend/moneyTrend")
public class MoneyTrendController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "")
    public String list(Model model){
        return "financialmanagement/moneytrend/moneyTrend";
    }


    @ResponseBody
    @RequestMapping("barGraph")
    public GsonOption getBarOption(String beginDate,String endDate,String zq){
        GsonOption barOption = new GsonOption();
        barOption.title().text("进账的订单个数和金额总数");
        barOption.tooltip().trigger(Trigger.axis);
        barOption.legend("订单个数", "金额总数");
        barOption.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true),new MagicType(Magic.line, Magic.pie).show(true), Tool.restore, Tool.saveAsImage);
        barOption.calculable(true);
        barOption.yAxis(new ValueAxis());
        Bar bar = new Bar("订单个数");

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
                Integer[] order = new Integer[weeks];
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
                    int orderNum = orderService.getOrderByDate(map);
                    order[i] = orderNum;
                    beginByWeek = endByWeek;
                }
                barOption.xAxis(new CategoryAxis().data(week));
                bar.setData(Arrays.asList(order));
            }
            else if("年".equals(zq)){
                //x轴
                int years = add/12;
                String[] year = new String[years];
                //y轴
                Integer[] order = new Integer[years];
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
                    int orderNum = orderService.getOrderByDate(map);
                    order[i] = orderNum;
                    beginByYear = endByYear;
                }
                barOption.xAxis(new CategoryAxis().data(year));
                bar.setData(Arrays.asList(order));
            }
            else if("月".equals(zq)){
                //x轴
                int months = Math.abs(add + result);
                String[] month = new String[months];
                //y轴
                Integer[] order = new Integer[months];
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
                    int orderNum = orderService.getOrderByDate(map);
                    order[i] = orderNum;
                    beginByMonth = endByMonth;
                }
                barOption.xAxis(new CategoryAxis().data(month));
                bar.setData(Arrays.asList(order));
            }
            else if("日".equals(zq)){
                //x轴
                String[] day = new String[days];
                //y轴
                Integer[] order = new Integer[days];
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
                    int orderNum = orderService.getOrderByDate(map);
                    order[i] = orderNum;
                    beginByDay = endByDay;
                }
                barOption.xAxis(new CategoryAxis().data(day));
                bar.setData(Arrays.asList(order));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar2 = new Bar("金额总数");
//        List<Double> list = Arrays.asList(2.6, 5.9, 9.0, 6.4, 8.7, 0.7, 5.6, 2.2, 8.7, 8.8, 6.0, 2.3);
//        bar2.setData(list);
//        bar2.markPoint().data(new PointData("年最高", 182.2).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2.3).xAxis(11).yAxis(3));
//        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        barOption.series(bar,bar2);
        return barOption;
    }
}
