package com.qxf.entity;

import java.io.Serializable;

/**
 * 权限(Permission)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 752387910865128984L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 权限路径
    */
    private String url;
    /**
    * 权限类型
    */
    private Integer type;
    /**
    * 父权限id
    */
    private String parentId;
    /**
    * 排序号
    */
    private Integer sort;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}