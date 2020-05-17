package com.qxf.controller;

import com.qxf.entity.FillQuestion;
import com.qxf.service.FillQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 填空题(FillQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("fillQuestion")
public class FillQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private FillQuestionService fillQuestionService;

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