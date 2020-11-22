package com.qxf.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qxf.entity.JudgeQuestion;
import com.qxf.service.JudgeQuestionService;
import com.qxf.util.QuestionLevelMap;

import java.util.*;

/**
 * @ClassName JudgeListener
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/21 23:00
 **/
public class JudgeListener extends AnalysisEventListener<Map<Integer,String>> {
    private List<JudgeQuestion> judgeQuestionList;
    private JudgeQuestionService judgeQuestionService;

    public JudgeListener(JudgeQuestionService judgeQuestionService){
        this.judgeQuestionService = judgeQuestionService;
        this.judgeQuestionList = new ArrayList<>();
    }
    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        JudgeQuestion question = new JudgeQuestion();
        question.setId(UUID.randomUUID().toString().replace("-",""));
        question.setCreateTime(new Date());
        question.setQuestionContent(map.get(0));
        question.setQuestionAnswer(map.get(1));
        question.setQuestionExplain(map.get(2));
        question.setQuestionLevel(QuestionLevelMap.getLevelByValue(map.get(3)));
        judgeQuestionList.add(question);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        judgeQuestionService.batchInsert(judgeQuestionList);
        judgeQuestionList.clear();
    }
}
