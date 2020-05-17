package com.qxf.controller;

import com.qxf.entity.Dict;
import com.qxf.service.DictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典(Dict)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:39
 */
@RestController
@RequestMapping("dict")
public class DictController {
    /**
     * 服务对象
     */
    @Resource
    private DictService dictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Dict selectOne(String id) {
        return this.dictService.queryById(id);
    }

}