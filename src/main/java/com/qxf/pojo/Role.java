package com.qxf.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 *@ClassName Role
 *@Description TODO
 *@Author qiuxinfa
 *@Date 2020/5/5 23:18
 **/
public class Role implements GrantedAuthority,Serializable{
    private String id;
    private String name;
    private String description;

    @Override
    public String getAuthority() {
        return name;
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

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
