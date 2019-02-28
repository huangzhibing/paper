package com.hqu.modules.papered_count.web;

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
import com.hqu.modules.papercount.service.PaperCountService;
import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.papermanage.service.PaperService;
import com.hqu.modules.paperreceive.receive.service.ReceiveService;
import com.jeeplus.modules.test.manytomany.service.StudentService;
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

@Controller
@RequestMapping(value = "${adminPath}/papered_count/papered_count")
public class papered_count {
    //@Autowired 我需要的表

    @Autowired
    ExpertService expertService;
    //
    @Autowired
    StudentService studentService;
    @Autowired
    PaperCountService paperCountService;
    @Autowired
    UniversityService universityService;


    @RequestMapping(value = {"", "list"})
    public String list(Model model) {
        return "/modules/papered_count/papered_countForm";
    }

    //柱状图
    @ResponseBody
    @RequestMapping("barGraph")
    public GsonOption getOption(String beginDate, String endDate, String lwlxdm) {

        List<University> university = universityService.selectAll_University();

        GsonOption option = new GsonOption();
        option.title().text("评审论文数量分布情况");
        option.tooltip().trigger(Trigger.axis);
        option.legend("学士论文", "硕士论文", "博士论文");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.pie, Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.yAxis(new ValueAxis());
        //画个x轴

        String[] university_name=new String[university.size()];
        Integer[] xslw_number = new Integer[university.size()];
        Integer[] sslw_number = new Integer[university.size()];
        Integer[] bslw_number = new Integer[university.size()];
        int a=0;

        Bar bar = new Bar("学士论文");
        Bar bar2 = new Bar("硕士论文");
        Bar bar3 = new Bar("博士论文");

        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            Date begin = sdf.parse(beginDate);
//            Date end = sdf.parse(endDate);
//            int days = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
//
//            Calendar bef = Calendar.getInstance();
//            Calendar aft = Calendar.getInstance();
//            bef.setTime(begin);
//            aft.setTime(end);
//            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
//            int add = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;

            if ("0".equals(lwlxdm)) {
                a = 0;
                for (University university1 : university) {
                    university_name[a] = university1.getGxmc();
                    int num = paperCountService.selectOneType_Paper(university1.getGxdm(), "0");
                    xslw_number[a] = num;

                    bar.data(xslw_number[a]);
                    a++;
                }
                option.xAxis(new CategoryAxis().data(university_name));
                bar.setData(Arrays.asList(xslw_number));
            } else if ("1".equals(lwlxdm)) {
                a = 0;
                for (University university1 : university) {
                    university_name[a] = university1.getGxmc();
                    int num = paperCountService.selectOneType_Paper(university1.getGxdm(), "1");
                    sslw_number[a] = num;

                    bar.data(sslw_number[a]);
                    a++;
                }
                option.xAxis(new CategoryAxis().data(university_name));
                bar.setData(Arrays.asList(sslw_number));
            } else if ("2".equals(lwlxdm)) {
                a = 0;
                for (University university1 : university) {
                    university_name[a] = university1.getGxmc();
                    int num = paperCountService.selectOneType_Paper(university1.getGxdm(), "2");
                    bslw_number[a] = num;

                    bar.data(bslw_number[a]);
                    a++;
                }
                option.xAxis(new CategoryAxis().data(university_name));
                bar.setData(Arrays.asList(bslw_number));
            }
//            catch (ParseException e) {
//                  e.printStackTrace();
//            }
        }catch (Exception e){

        }
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        bar2.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));
        bar3.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar3.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        option.series(bar, bar2, bar3);
        //

        return option;
    }

}