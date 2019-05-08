package com.hqu.modules.reviewsearch.service;

import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;
import com.hqu.modules.basedata.specialitymanage.service.SpecialityManageService;
import com.hqu.modules.customermanage.ordermanage.entity.OrderManage;
import com.hqu.modules.customermanage.ordermanage.service.OrderManageService;
import com.hqu.modules.papereview.order.entity.Order;
import com.hqu.modules.papereview.order.service.OrderService;
import com.hqu.modules.papermanage.entity.Paper;
import com.hqu.modules.papermanage.service.PaperService;
import com.hqu.modules.paperreview.entity.PaperReview;
import com.hqu.modules.paperreview.service.PaperReviewService;
import com.hqu.modules.reviewsearch.entity.ReviewSearch;
import com.hqu.modules.reviewsearch.mapper.ReviewSearchMapper;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReviewSearchService extends CrudService<ReviewSearchMapper, ReviewSearch> {

    @Autowired
    public PaperService paperService;

    @Autowired
    public OrderService orderService;

    @Autowired
    public SpecialityManageService specialityManageService;

    public void bookInfo(String num, Map<String, String> map){
        ReviewSearch reviewSearch = new ReviewSearch();
        reviewSearch = mapper.bookInfo(num);
        map.put("readerName",reviewSearch.getReaderName());
        map.put("professionalTitle",reviewSearch.getProfessionalTitle());
        map.put("major",reviewSearch.getMajor());
        map.put("company",reviewSearch.getCompany());
        map.put("code",reviewSearch.getCode());
        map.put("phone",reviewSearch.getPhone());
        map.put("Email",reviewSearch.getEmail());
        map.put("number",reviewSearch.getNumber());
        map.put("paperName",reviewSearch.getPaperName());
        map.put("college","计算机科学与技术学院");     //没有学院

        Paper paper = paperService.get(num);
        if(paper.getXSXH() != null){
            if(StringUtils.isNotBlank(paper.getXSXH().getXsxm())) {
                map.put("stuName",paper.getXSXH().getXsxm());
                if(paper.getXSXH().getSpecialityManage() != null){
                    List<SpecialityManage> specialityManages = specialityManageService.findList(paper.getXSXH().getSpecialityManage());
                    if(StringUtils.isNotBlank(specialityManages.get(0).getZymc())){
                        map.put("stuMajor",specialityManages.get(0).getZymc());
                    }
                }else {
                    map.put("stuMajor",null);
                }
            }
        }else {
            map.put("stuName",null);
        }
        Order order = new Order();
        order.setLw(paper);
        order.setDdztdm("a");
        List<Order> list = orderService.findList(order);

        if(list.get(0).getLwxtfs() != null){
            map.put("score1", String.valueOf(list.get(0).getLwxtfs()));
        }
        if(list.get(0).getWxzsfs() != null){
            map.put("score2", String.valueOf(list.get(0).getWxzsfs()));
        }
        if(list.get(0).getZyspfs() != null){
           map.put("score3", String.valueOf(list.get(0).getZyspfs()));
        }
        if(list.get(0).getYjgzfs() != null){
            map.put("score4", String.valueOf(list.get(0).getYjgzfs()));
        }
        if(list.get(0).getYjcgfs() != null){
            map.put("score5", String.valueOf(list.get(0).getYjcgfs()));
        }
        if(list.get(0).getLwxzfs() != null){
            map.put("score6", String.valueOf(list.get(0).getLwxzfs()));
        }
        if(list.get(0).getZf() != null){
            map.put("score7", String.valueOf(list.get(0).getZf()));
            double score = list.get(0).getZf();
            if(score>=90){
                map.put("check1","On");
            }else if(score>=80&&score<89){
                map.put("check2","On");
            }else if(score>=70&&score<79){
                map.put("check3","On");
            }else if(score<70){
                map.put("check4","On");
            }
        }
        map.put("advise",list.get(0).getPsyj());
        Date updateTime = list.get(0).getUpdateDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(updateTime);

        map.put("year", String.valueOf(calendar.get(Calendar.YEAR)));
        map.put("month", String.valueOf(calendar.get(Calendar.MONTH)+1));
        map.put("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

    }
}
