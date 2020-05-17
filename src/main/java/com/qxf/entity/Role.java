package com.qxf.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * 角色(Role)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class Role implements Serializable,GrantedAuthority {
    private static final long serialVersionUID = -93092660464974071L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 角色名称
    */
    private String name;
    /**
    * 角色描述
    */
    private String description;

    @Override
    public String getAuthority() {
        return getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}