package com.qxf.controller;

import com.qxf.entity.MultiQuestion;
import com.qxf.service.MultiQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 多选题(MultiQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("multiQuestion")
public class MultiQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private MultiQuestionService multiQuestionService;

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