package com.qxf.entity;

import java.io.Serializable;

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
    private Integer singleScore;
    /**
    * 多选题分数
    */
    private Integer multiScore;
    /**
    * 判断题分数
    */
    private Integer judgeScore;
    /**
    * 填空题分数
    */
    private Integer fillScore;
    /**
    * 总分
    */
    private Integer totalScore;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(Integer singleScore) {
        this.singleScore = singleScore;
    }

    public Integer getMultiScore() {
        return multiScore;
    }

    public void setMultiScore(Integer multiScore) {
        this.multiScore = multiScore;
    }

    public Integer getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Integer judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Integer getFillScore() {
        return fillScore;
    }

    public void setFillScore(Integer fillScore) {
        this.fillScore = fillScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
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

}