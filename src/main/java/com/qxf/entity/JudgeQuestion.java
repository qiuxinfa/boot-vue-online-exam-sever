package com.qxf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 判断题(JudgeQuestion)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class JudgeQuestion implements Serializable {
    private static final long serialVersionUID = 454945685977456114L;
    /**
    * id
    */
    private String id;
    /**
    * 题目创建人ID
    */
    private String createId;
    /**
    * 题目创建时间
    */
    private Date createTime;
    /**
    * 题目更新人ID
    */
    private String updateId;
    /**
    * 题目更新时间
    */
    private Date updateTime;
    /**
    * 题目内容
    */
    private String questionContent;
    /**
    * 题目答案
    */
    private String questionAnswer;
    /**
    * 题目解析
    */
    private String questionExplain;
    /**
    * 题目难度等级
    */
    private Integer questionLevel;
    /**
    * 题目分类
    */
    private Integer questionType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionExplain() {
        return questionExplain;
    }

    public void setQuestionExplain(String questionExplain) {
        this.questionExplain = questionExplain;
    }

    public Integer getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(Integer questionLevel) {
        this.questionLevel = questionLevel;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

}