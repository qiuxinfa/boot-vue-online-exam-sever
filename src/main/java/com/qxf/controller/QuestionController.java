package com.qxf.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.annotation.MyLog;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.FillQuestion;
import com.qxf.service.FillQuestionService;
import com.qxf.service.JudgeQuestionService;
import com.qxf.service.MultiQuestionService;
import com.qxf.service.SingleQuestionService;
import com.qxf.util.EnumCode;
import com.qxf.util.FileUtils;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName QuestionController
 * @Description 题库管理
 * @Author qiuxinfa
 * @Date 2020/5/24 21:30
 **/
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private SingleQuestionService singleQuestionService;

    @Autowired
    private MultiQuestionService multiQuestionService;

    @Autowired
    private FillQuestionService fillQuestionService;

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    @GetMapping("/list")
    @MyLog
    public Object getListByPage(Integer startPage,Integer pageSize,String content,String questionType){
        PageHelper.startPage(startPage,pageSize);
        List<QuestionDto> list = null;
        //根据类型查询题库
        if ("second".equals(questionType)){
            list = judgeQuestionService.getListByPage(content);
        }else if("third".equals(questionType)){
            list = singleQuestionService.getListByPage(content);
        }else if("fourth".equals(questionType)){
            list = multiQuestionService.getListByPage(content);
        }else {
            //默认查询填空题
            list = fillQuestionService.getListByPage(content);
        }

        PageInfo<QuestionDto> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).autoCloseStream(Boolean.FALSE).build();
            // 指定要导出的属性
            Set<String> names = new HashSet<>(4);
            names.add("questionContent");
            names.add("questionAnswer");
            names.add("questionExplain");
            names.add("questionLevelStr");

            // 1. 填空题
            WriteSheet fillSheet = EasyExcel.writerSheet(0, "填空题").head(QuestionDto.class)
                    .includeColumnFiledNames(names).build();
            List<QuestionDto> fillData = fillQuestionService.getListByPage(null);
            excelWriter.write(fillData,fillSheet);
            // 2. 判断题
            WriteSheet judgeSheet = EasyExcel.writerSheet(1, "判断题").head(QuestionDto.class)
                    .includeColumnFiledNames(names).build();
            List<QuestionDto> judgeData = judgeQuestionService.getListByPage(null);
            excelWriter.write(judgeData,judgeSheet);

            // 选择题导出的属性，增加四个选项
            names.add("choiceA");
            names.add("choiceB");
            names.add("choiceC");
            names.add("choiceD");

            // 3. 单选题
            WriteSheet singleSheet = EasyExcel.writerSheet(2, "单选题").head(QuestionDto.class)
                    .includeColumnFiledNames(names).build();
            List<QuestionDto> singleData = singleQuestionService.getListByPage(null);
            excelWriter.write(singleData,singleSheet);
            // 4. 多选题
            WriteSheet multiSheet = EasyExcel.writerSheet(3, "多选题").head(QuestionDto.class)
                    .includeColumnFiledNames(names).build();
            List<QuestionDto> multiData = multiQuestionService.getListByPage(null);
            excelWriter.write(multiData,multiSheet);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    
    @PostMapping("/template/download")
    public void downloadTemplate(HttpServletResponse response){
        Resource resource = resourceLoader.getResource("classpath:template/questionTemplate.xlsx");
        try {
            FileUtils.downloadFile(resource.getFile(),response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
