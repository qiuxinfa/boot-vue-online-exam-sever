package com.qxf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试安排(Exam)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class Exam implements Serializable {
    private static final long serialVersionUID = 673421218970161513L;
    /**
    * id
    */
    private String id;

    /*
     *试卷名称
     **/
    private String name;

    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
    * 考试介绍
    */
    private String examDesc;
    /**
    * 考试开始日期
    */
    private Object startDate;
    /**
    * 考试截止日期
    */
    private Object endDate;
    /**
    * 考试时长
    */
    private Integer totalTime;
    /**
    * 专业
    */
    private String major;
    /**
    * 学院
    */
    private String institute;
    /**
    * 考试类型
    */
    private Integer examType;
    /**
    * 单选题分数
    */
    private Double singleScore;
    /**
    * 多选题分数
    */
    private Double multiScore;
    /**
    * 判断题分数
    */
    private Double judgeScore;
    /**
    * 填空题分数
    */
    private Double fillScore;
    /**
    * 总分
    */
    private Double totalScore;
    /**
    * 单选题id集合
    */
    private String singleIds;
    /**
    * 多选题id集合
    */
    private String multiIds;
    /**
    * 判断题id集合
    */
    private String judgeIds;
    /**
    * 填空题id集合
    */
    private String fillIds;

    /**
     * 是否发布：0未发布，1已发布（发布的试卷，才可以考试）
     */
    private Integer isPublish;

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

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Double getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(Double singleScore) {
        this.singleScore = singleScore;
    }

    public Double getMultiScore() {
        return multiScore;
    }

    public void setMultiScore(Double multiScore) {
        this.multiScore = multiScore;
    }

    public Double getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Double judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Double getFillScore() {
        return fillScore;
    }

    public void setFillScore(Double fillScore) {
        this.fillScore = fillScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getSingleIds() {
        return singleIds;
    }

    public void setSingleIds(String singleIds) {
        this.singleIds = singleIds;
    }

    public String getMultiIds() {
        return multiIds;
    }

    public void setMultiIds(String multiIds) {
        this.multiIds = multiIds;
    }

    public String getJudgeIds() {
        return judgeIds;
    }

    public void setJudgeIds(String judgeIds) {
        this.judgeIds = judgeIds;
    }

    public String getFillIds() {
        return fillIds;
    }

    public void setFillIds(String fillIds) {
        this.fillIds = fillIds;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}