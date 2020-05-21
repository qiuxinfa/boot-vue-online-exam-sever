package com.qxf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试记录表(ExamRecord)实体类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public class ExamRecord implements Serializable {
    private static final long serialVersionUID = 683283958918762497L;
    /**
    * 考试记录表的主键
    */
    private String id;
    /**
    * 考试安排ID
    */
    private String examId;
    /**
    * 考试参与者的用户id
    */
    private String userId;
    /**
    * 考试完成时间
    */
    private Date finishTime;
    /**
    * 完成考试所用的时间,单位分钟
    */
    private Integer costTime;
    /**
    * 参与考试的实际得分
    */
    private Double finalScore;
    /**
    * 考试结果的等级
    */
    private Integer resultLevel;
    /**
    * 考生单选题答案，题与题之间用*号分隔
    */
    private String singleAnswer;
    /**
    * 考生多选题答案，题与题之间用*号分隔，同一个题的每2个选项之间按序用#号分隔
    */
    private String multiAnswer;
    /**
    * 考生判断题答案，题与题之间用*号分隔
    */
    private String judgeAnswer;
    /**
    * 考生填空题答案，题与题之间用*号分隔，同一个题的2个空之间用#号分隔
    */
    private String fillAnswer;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getResultLevel() {
        return resultLevel;
    }

    public void setResultLevel(Integer resultLevel) {
        this.resultLevel = resultLevel;
    }

    public String getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(String singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public String getMultiAnswer() {
        return multiAnswer;
    }

    public void setMultiAnswer(String multiAnswer) {
        this.multiAnswer = multiAnswer;
    }

    public String getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setJudgeAnswer(String judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public String getFillAnswer() {
        return fillAnswer;
    }

    public void setFillAnswer(String fillAnswer) {
        this.fillAnswer = fillAnswer;
    }

}