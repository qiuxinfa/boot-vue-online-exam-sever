package com.qxf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.dto.ExamRecordDto;
import com.qxf.entity.ExamRecord;
import com.qxf.service.ExamRecordService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试记录表(ExamRecord)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("examRecord")
public class ExamRecordController {
    /**
     * 服务对象
     */
    @Resource
    private ExamRecordService examRecordService;

    /*
     * @Author qiuxinfa
     * @Description 添加考试记录
     * @Date  2020/5/20 19:26
     * @Param [examRecord]
     * @return java.lang.Object
     **/
    @PostMapping("/add")
    public Object addExamRecord(@RequestBody ExamRecord examRecord){
        Double finalScore = examRecord.getFinalScore();
        examRecordService.insert(examRecord);
        return new ResultUtil(EnumCode.OK.getValue(),"考试结束，此次考试成绩为： "+finalScore+" 分");
    }

    //分页查询考试记录列表
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String name, String userId){
        PageHelper.startPage(startPage,pageSize);
        //查询自己的考试记录
        List<ExamRecordDto> list = examRecordService.getListByPage(name,userId);
        PageInfo<ExamRecordDto> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ExamRecord selectOne(String id) {
        return this.examRecordService.queryById(id);
    }

}