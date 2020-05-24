package com.qxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.MultiQuestion;
import com.qxf.service.MultiQuestionService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选题(MultiQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("multi")
public class MultiQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private MultiQuestionService multiQuestionService;

    // 分页查询
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String content){
        PageHelper.startPage(startPage,pageSize);
        List<QuestionDto> list = multiQuestionService.getListByPage(content);
        PageInfo<QuestionDto> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MultiQuestion selectOne(String id) {
        return this.multiQuestionService.queryById(id);
    }

}