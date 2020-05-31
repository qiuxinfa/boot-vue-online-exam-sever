package com.qxf.controller;

import com.qxf.annotation.MyLog;
import com.qxf.entity.Role;
import com.qxf.service.RoleService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/23 16:25
 **/
@RestController
@RequestMapping("role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    @MyLog
    public ResultUtil getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        return new ResultUtil(EnumCode.OK.getValue(),"获取角色列表成功",roleList);
    }
}
