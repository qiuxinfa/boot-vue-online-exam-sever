package com.qxf.pojo;

import java.io.Serializable;

/**
 * @ClassName Permission
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:25
 **/
public class Permission implements Serializable{
    private String id;
    private String name;
    private String url;
    private Integer type;
    private String parentId;
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

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", parentId='" + parentId + '\'' +
                ", sort=" + sort +
                '}';
    }
}
