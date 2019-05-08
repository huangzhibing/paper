package com.jeeplus.common.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfMaker {

    private static Logger logger = LoggerFactory.getLogger(PdfMaker.class);
    public static void fillTemplate(Map<String,String> map,PdfReader reader, String newPDFPath) {
        logger.info("开始生成生成pdf文件");
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            // 输出流
            out = new FileOutputStream(newPDFPath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(font);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                logger.info(entry.getValue());
                form.setField(entry.getKey(), entry.getValue());
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int pages = reader.getNumberOfPages();
            for (int i = 0; i < pages; i++) {
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()),i + 1);
                copy.addPage(importPage);
            }
            doc.close();

        } catch (IOException | DocumentException e) {
            logger.error(newPDFPath+e.getMessage(),e);
        }

    }

    public static void main(String[] args) {
        /**
         * readerName  评阅人姓名
         professionalTitle 职称
         major 专家学科专业
         company 工作单位
         code 通讯地址，邮政编码
         phone 联系电话
         Email 邮件
         number 编号
         paperName 论文
         papername1 论文1
         college 学院
         stuMajor 研究生学科专业
         stuName 研究生姓名
         signReaderName 评阅人姓名
         year 评阅年份
         month 评阅月份
         day 评阅日
         score1
         score2
         score3
         score4
         score5
         score6
         score7 总分
         check1
         check2
         check3
         check6
         advise
         */
        Map<String,String> map = new HashMap<>(16);
        map.put("readerName","黄有财");
        map.put("professionalTitle","学生");
        map.put("major","2财111111111");
        map.put("company","华大");
        map.put("code","333300");
        map.put("phone","18850042980");
        map.put("Email","1359646044@qq.com");
        map.put("number","1122334455");
        map.put("paperName","读大学的重要性研究");
        map.put("college","计算机");
        map.put("stuMajor","计算机软件工程");
        map.put("stuName","刘某某");
        // 模板路径
        String templatePath = "C:\\Users\\jack\\Desktop\\a.pdf";
        // 生成的新文件路径
        String newPDFPath = "C:\\Users\\jack\\Desktop\\"+Math.random()+".pdf";
        PdfReader reader;
        try {
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            fillTemplate(map,reader,newPDFPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
