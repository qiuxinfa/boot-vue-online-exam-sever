package com.qxf.entity;

import java.io.Serializable;

/**
 * 字典(Dict)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:33
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = -73404928967728707L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 字典代码
    */
    private String dictCode;
    /**
    * 字典值
    */
    private String dictValue;
    /**
    * 字典类型代码
    */
    private String dictTypeCode;
    /**
    * 字典描述
    */
    private String dictDesc;
    /**
    * 字典排序
    */
    private Integer dictOrder;
    
    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public Integer getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(Integer dictOrder) {
        this.dictOrder = dictOrder;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}