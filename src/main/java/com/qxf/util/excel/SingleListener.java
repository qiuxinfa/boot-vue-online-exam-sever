package com.qxf.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qxf.entity.SingleQuestion;
import com.qxf.service.SingleQuestionService;
import com.qxf.util.QuestionLevelMap;

import java.util.*;

/**
 * @ClassName SingleListener
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/21 23:00
 **/
public class SingleListener extends AnalysisEventListener<Map<Integer,String>> {
    private List<SingleQuestion> singleQuestionList;
    private SingleQuestionService singleQuestionService;

    public SingleListener(SingleQuestionService singleQuestionService){
        this.singleQuestionService = singleQuestionService;
        this.singleQuestionList = new ArrayList<>();
    }

    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        SingleQuestion question = new SingleQuestion();
        question.setId(UUID.randomUUID().toString().replace("-",""));
        question.setCreateTime(new Date());
        question.setQuestionContent(map.get(0));
        question.setChoiceA(map.get(1));
        question.setChoiceB(map.get(2));
        question.setChoiceC(map.get(3));
        question.setChoiceD(map.get(4));
        question.setQuestionAnswer(map.get(5));
        question.setQuestionExplain(map.get(6));
        question.setQuestionLevel(QuestionLevelMap.getLevelByValue(map.get(7)));
        singleQuestionList.add(question);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        singleQuestionService.batchInsert(singleQuestionList);
        singleQuestionList.clear();
    }
}
