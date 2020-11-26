package com.qxf.dto;

import java.io.Serializable;

/**
 * @ClassName PaperDto
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/27 19:44
 **/
public class PaperDto implements Serializable{
    private String id;
    private String name;
    private String examDesc;
    private Integer fillNumber;
    private Integer judgeNumber;
    private Integer singleNumber;
    private Integer multiNumber;
    private Double fillScore;
    private Double judgeScore;
    private Double singleScore;
    private Double multiScore;
    private Integer totalTime;
    private Integer isPublish;
    private String fillIds;
    private String judgeIds;
    private String singleIds;
    private String multiIds;

    public String getFillIds() {
        return fillIds;
    }

    public void setFillIds(String fillIds) {
        this.fillIds = fillIds;
    }

    public String getJudgeIds() {
        return judgeIds;
    }

    public void setJudgeIds(String judgeIds) {
        this.judgeIds = judgeIds;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public Integer getFillNumber() {
        return fillNumber;
    }

    public void setFillNumber(Integer fillNumber) {
        this.fillNumber = fillNumber;
    }

    public Integer getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(Integer judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public Integer getSingleNumber() {
        return singleNumber;
    }

    public void setSingleNumber(Integer singleNumber) {
        this.singleNumber = singleNumber;
    }

    public Integer getMultiNumber() {
        return multiNumber;
    }

    public void setMultiNumber(Integer multiNumber) {
        this.multiNumber = multiNumber;
    }

    public Double getFillScore() {
        return fillScore;
    }

    public void setFillScore(Double fillScore) {
        this.fillScore = fillScore;
    }

    public Double getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Double judgeScore) {
        this.judgeScore = judgeScore;
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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }
}
