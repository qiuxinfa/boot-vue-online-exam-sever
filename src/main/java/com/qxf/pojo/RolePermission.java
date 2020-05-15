package com.qxf.pojo;

import java.io.Serializable;

/**
 * @ClassName RolePermission
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:28
 **/
public class RolePermission implements Serializable{
    private String id;
    private String roleId;
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

    @Override
    public String toString() {
        return "RolePermission{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}
