package com.qxf.controller;

import com.qxf.entity.UserRole;
import com.qxf.service.UserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户-角色(UserRole)表控制层
 *
 * @author makejava
 * @since 2020-05-17 11:25:41
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(String id) {
        return this.userRoleService.queryById(id);
    }

}