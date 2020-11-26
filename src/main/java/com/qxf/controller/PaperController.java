package com.qxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.annotation.MyLog;
import com.qxf.dto.PaperDto;
import com.qxf.entity.Exam;
import com.qxf.service.ExamService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PaperController
 * @Description 试卷管理
 * @Author qiuxinfa
 * @Date 2020/5/26 23:30
 **/
@RestController
@RequestMapping("paper")
public class PaperController {
    @Resource
    private ExamService examService;

    //分页查询试卷列表
    @GetMapping("/list")
    @MyLog
    public Object getListByPage(Integer startPage,Integer pageSize,String name){
        PageHelper.startPage(startPage,pageSize);
        List<Exam> list = examService.getListByPage(name,null);
        PageInfo<Exam> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    //随机组卷
    @PostMapping("/add")
    @MyLog
    public ResultUtil addPaperByRandom(@RequestBody PaperDto paperDto){
        return examService.addPaperByRandom(paperDto);
    }

    //自由选题
    @PostMapping("/createPaper")
    @MyLog
    public ResultUtil createPaper(@RequestBody PaperDto paperDto){
        return examService.createPaper(paperDto);
    }

    @PostMapping("/update")
    @MyLog
    public ResultUtil updatePaper(@RequestBody PaperDto paperDto){
        Integer cnt = examService.updatePaper(paperDto);
        String msg = "发布考试失败";
        if (cnt > 0){
            msg = "发布考试成功";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }

    @GetMapping("/count")
    @MyLog
    public ResultUtil countByType(){
        Map<String, Integer> count = examService.getCount();
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",count);
    }
}
