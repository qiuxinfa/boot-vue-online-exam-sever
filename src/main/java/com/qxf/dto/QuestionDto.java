package com.qxf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName QuestionDto
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/24 21:33
 **/
public class QuestionDto implements Serializable{
    private String id;
    private String content;   //题目
    private Date createTime;
    private Date updateTime;
    private String level;    //难度等级
    private String typeStr;  //问题类型，字符串
    private Integer type;    //问题类型，整形

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
