package com.qxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.FillQuestion;
import com.qxf.service.FillQuestionService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 填空题(FillQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("fill")
public class FillQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private FillQuestionService fillQuestionService;

    // 分页查询
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String content){
        PageHelper.startPage(startPage,pageSize);
        List<QuestionDto> list = fillQuestionService.getListByPage(content);
        PageInfo<QuestionDto> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    public ResultUtil add(@RequestBody FillQuestion fillQuestion){
        fillQuestion.setId(UUID.randomUUID().toString().replace("-",""));
        FillQuestion insert = fillQuestionService.insert(fillQuestion);
        if (insert != null){
            return new ResultUtil(EnumCode.OK.getValue(),"请求成功");
        }else {
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"请求失败");
        }

    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FillQuestion selectOne(String id) {
        return this.fillQuestionService.queryById(id);
    }

}