package com.qxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.service.FillQuestionService;
import com.qxf.service.JudgeQuestionService;
import com.qxf.service.MultiQuestionService;
import com.qxf.service.SingleQuestionService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

//    @GetMapping("/list")
//    public Object getListByPage(Integer startPage,Integer pageSize,String content){
//        PageHelper.startPage(startPage,pageSize);
//        //查询自己的考试记录
////        List<QuestionDto> list = examRecordService.getListByPage(name,userId);
////        PageInfo<QuestionDto> pageInfo = new PageInfo<>(list);
////        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
//    }
}
