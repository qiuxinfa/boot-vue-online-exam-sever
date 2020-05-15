package com.qxf.pojo;

import java.io.Serializable;

/**
 * @ClassName Dict
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:30
 **/
public class Dict implements Serializable{
    private String id;
    private String dictCode;
    private String dictValue;
    private String dictTypeCode;
    private String dictDesc;
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

    @Override
    public String toString() {
        return "Dict{" +
                "id='" + id + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictTypeCode='" + dictTypeCode + '\'' +
                ", dictDesc='" + dictDesc + '\'' +
                ", dictOrder=" + dictOrder +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
