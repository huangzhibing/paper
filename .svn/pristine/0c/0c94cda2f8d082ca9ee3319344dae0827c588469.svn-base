package com.hqu.modules.papercount.web;

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
import com.github.abel533.echarts.series.Pie;
import com.hqu.modules.papercount.entity.PaperCount;
import com.hqu.modules.papercount.service.PaperCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author nzx
 */
@Controller
@RequestMapping(value = "${adminPath}/papercount")
public class PaperCountController {

    @Autowired
    private PaperCountService paperCountService;

    @RequestMapping(value = {"","list"})
    public String list(Model model){
        return "/modules/papercount/papercountForm";
    }

    @ResponseBody
    @RequestMapping("bar")
    public GsonOption getOption(@RequestParam(value = "beginDate",required = false)String beginDate, @RequestParam(value = "endDate",required = false)String endDate, @RequestParam(value = "lwlxdm",required = false)String lwlxdm, @RequestParam(value = "lwztdm",required = false)String lwztdm, @RequestParam(value = "lwmc",required = false)String lwmc, @RequestParam(value = "flag",required = false)String flag) {
        PaperCount pcount = new PaperCount();
        PaperCount allsc = new PaperCount();

        if(flag.equals("1")) {
            if(lwmc.equals("undefined")) lwmc = "";
            //pcount.setLWZTDM(lwztdm);
            //pcount.setLWMC(lwmc);
            pcount.setBeginCreateDate(beginDate);
            pcount.setEndCreateDate(endDate);
            //pcount.setLWLXDM(lwlxdm);

            //allsc.setLWZTDM(lwztdm);
            //allsc.setLWMC(lwmc);
            allsc.setBeginCreateDate(beginDate);
            allsc.setEndCreateDate(endDate);
            //allsc.setLWLXDM(lwlxdm);
        }
        List<PaperCount> plist = paperCountService.findList(pcount);
        List<PaperCount> allscname = paperCountService.getScName(allsc);

        GsonOption option = new GsonOption();
        option.title().text("论文统计");
        option.tooltip().trigger(Trigger.axis);
        option.legend("学士论文", "硕士论文", "博士论文");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.pie, Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        String[] ScName=new String[allscname.size()];
        Integer[] xslw = new Integer[allscname.size()];
        Integer[] sslw = new Integer[allscname.size()];
        Integer[] bslw = new Integer[allscname.size()];

        for(int i=0;i<allscname.size();i++){
            xslw[i] = sslw[i] = bslw[i] = 0 ;
        }

        for(int i=0;i< plist.size();i++){
            for(int j=0;j<allscname.size();j++){
                if(plist.get(i).getGxmc().equals(allscname.get(j).getGxmc())){
                    if(plist.get(i).getLWLXDM().equals("0")){ xslw[j]++; }
                    else if(plist.get(i).getLWLXDM().equals("1")) { sslw[j]++; }
                    else{ bslw[j]++; }
                }
            }
        }

        for(int i=0;i<allscname.size();i++){
            ScName[i] = allscname.get(i).getGxmc();
        }

        option.xAxis(new CategoryAxis().data(ScName));
        option.yAxis(new ValueAxis());

        Bar bar = new Bar("学士论文");
        bar.setData(Arrays.asList(xslw));
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar1 = new Bar("硕士论文");
        bar1.data(sslw);
        bar1.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar1.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar2 = new Bar("博士论文");
        /*List<Double> list = Arrays.asList(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3);
        bar2.setData(list);*/
        bar2.data(bslw);
        bar2.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        /*bar2.markPoint().data(new PointData("年最高", 182.2).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2.3).xAxis(11).yAxis(3));*/
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        option.series(bar,bar1,bar2);
        return option;
    }

}
