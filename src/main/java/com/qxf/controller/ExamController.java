package com.qxf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.annotation.MyLog;
import com.qxf.entity.Exam;
import com.qxf.service.ExamService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 考试安排(Exam)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("exam")
public class ExamController {
    /**
     * 服务对象
     */
    @Resource
    private ExamService examService;

    //分页查询考试列表
    @MyLog
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String name){
        PageHelper.startPage(startPage,pageSize);
        List<Exam> list = examService.getListByPage(name,1);
        PageInfo<Exam> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    /*
     * @Author qiuxinfa
     * @Description 获取试卷详情
     * @Date  2020/5/17 21:36
     * @Param [exam]
     * @return java.lang.Object
     **/
    @GetMapping("/detail")
    @MyLog
    public Object getExamDetail(Exam exam){
        Map<String, List<?>> examDetail = examService.getExamDetail(exam);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",examDetail);
    }
}