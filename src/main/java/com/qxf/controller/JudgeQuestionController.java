package com.qxf.controller;

import com.qxf.entity.JudgeQuestion;
import com.qxf.service.JudgeQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 判断题(JudgeQuestion)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("judgeQuestion")
public class JudgeQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private JudgeQuestionService judgeQuestionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JudgeQuestion selectOne(String id) {
        return this.judgeQuestionService.queryById(id);
    }

}