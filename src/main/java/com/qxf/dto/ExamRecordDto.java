package com.qxf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ExamRecordDto
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/20 19:38
 **/
public class ExamRecordDto implements Serializable{
    private String id;
    //参加考试的人
    private String username;
    /**
     * 参与考试的试卷名称
     */
    private String examName;
    /**
     * 参与考试的实际得分
     */
    private Double finalScore;

    /**
     * 考试结果的等级
     */
    private String resultLevel;
    /**
     * 提交时间的时间
     */
    private Date finishTime;
    /**
     * 完成考试所用的时间,单位分钟
     */
    private Integer costTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getResultLevel() {
        return resultLevel;
    }

    public void setResultLevel(String resultLevel) {
        this.resultLevel = resultLevel;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }
}
