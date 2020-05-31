package com.qxf.controller;

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
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName QuestionController
 * @Description 题库管理
 * @Author qiuxinfa
 * @Date 2020/5/24 21:30
 **/
@RestController
@RequestMapping("question")
public class QuestionController {
    @Resource
    private SingleQuestionService singleQuestionService;

    @Resource
    private MultiQuestionService multiQuestionService;

    @Resource
    private FillQuestionService fillQuestionService;

    @Resource
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

}
