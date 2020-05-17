package com.qxf.dao;

import com.qxf.entity.Role;

import java.util.List;

public interface RoleDao {
    /*
     * @Author qiuxinfa
     * @Description 根据用户id，查询角色列表
     * @Date  2020/5/6 22:15
     * @Param [userId]
     * @return java.util.List<com.qxf.pojo.Role>
     **/
    List<Role> getRolesByUserId(String userId);
}
