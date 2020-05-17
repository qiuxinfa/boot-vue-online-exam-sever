package com.qxf.entity;

import java.io.Serializable;

/**
 * 角色-权限(RolePermission)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -24408052380504446L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 角色id
    */
    private String roleId;
    /**
    * 鏉冮檺id
    */
    private String permissionId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}