package com.qxf.controller;

import com.qxf.entity.RolePermission;
import com.qxf.service.RolePermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色-权限(RolePermission)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RolePermission selectOne(String id) {
        return this.rolePermissionService.queryById(id);
    }

}