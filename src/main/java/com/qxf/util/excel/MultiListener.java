package com.qxf.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qxf.entity.MultiQuestion;
import com.qxf.service.MultiQuestionService;
import com.qxf.util.QuestionLevelMap;

import java.util.*;

/**
 * @ClassName MultiListener
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/21 23:01
 **/
public class MultiListener extends AnalysisEventListener<Map<Integer,String>> {
    private List<MultiQuestion> multiQuestionList;
    private MultiQuestionService multiQuestionService;

    public MultiListener(MultiQuestionService multiQuestionService){
        this.multiQuestionService = multiQuestionService;
        this.multiQuestionList = new ArrayList<>();
    }

    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        MultiQuestion question = new MultiQuestion();
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
        multiQuestionList.add(question);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        multiQuestionService.batchInsert(multiQuestionList);
        multiQuestionList.clear();
    }
}
