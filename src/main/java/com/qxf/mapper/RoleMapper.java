package com.qxf.mapper;

import com.qxf.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /*
     * @Author qiuxinfa
     * @Description 根据用户id，查询角色列表
     * @Date  2020/5/6 22:15
     * @Param [userId]
     * @return java.util.List<com.qxf.pojo.Role>
     **/
    List<Role> getRolesByUserId(String userId);
}
