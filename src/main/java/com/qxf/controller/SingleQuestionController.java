package com.qxf.controller;

import com.qxf.entity.SingleQuestion;
import com.qxf.service.SingleQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 单选题(SingleQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("singleQuestion")
public class SingleQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private SingleQuestionService singleQuestionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SingleQuestion selectOne(String id) {
        return this.singleQuestionService.queryById(id);
    }

}