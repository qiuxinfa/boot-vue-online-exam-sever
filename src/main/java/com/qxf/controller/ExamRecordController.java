package com.qxf.controller;

import com.qxf.entity.ExamRecord;
import com.qxf.service.ExamRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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