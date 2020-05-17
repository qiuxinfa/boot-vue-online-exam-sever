package com.qxf.entity;

import java.io.Serializable;

/**
 * 用户-角色(UserRole)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -32367301089510524L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 用户id
    */
    private String userId;
    /**
    * 角色id
    */
    private String roleId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}