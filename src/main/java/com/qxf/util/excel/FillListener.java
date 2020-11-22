package com.qxf.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qxf.entity.FillQuestion;
import com.qxf.service.FillQuestionService;
import com.qxf.util.QuestionLevelMap;

import java.util.*;

/**
 * @ClassName FillListener
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/21 22:58
 **/
public class FillListener extends AnalysisEventListener<Map<Integer,String>>{
    private List<FillQuestion> fillQuestionList;
    private FillQuestionService fillQuestionService;

    public FillListener(FillQuestionService questionService){
        this.fillQuestionService = questionService;
        this.fillQuestionList = new ArrayList<>();
    }

    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        FillQuestion question = new FillQuestion();
        question.setId(UUID.randomUUID().toString().replace("-",""));
        question.setCreateTime(new Date());
        question.setQuestionContent(map.get(0));
        question.setQuestionAnswer(map.get(1));
        question.setQuestionExplain(map.get(2));
        question.setQuestionLevel(QuestionLevelMap.getLevelByValue(map.get(3)));
        fillQuestionList.add(question);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 将数据插入数据库
        fillQuestionService.batchInsert(fillQuestionList);
        fillQuestionList.clear();
    }
}
