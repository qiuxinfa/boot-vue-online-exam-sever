package com.qxf.service.impl;

import com.github.pagehelper.Page;
import com.qxf.dao.*;
import com.qxf.dto.PaperDto;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.*;
import com.qxf.service.ExamService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 考试安排(Exam)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamDao examDao;

    @Resource
    private SingleQuestionDao singleQuestionDao;

    @Resource
    private MultiQuestionDao multiQuestionDao;

    @Resource
    private JudgeQuestionDao judgeQuestionDao;

    @Resource
    private FillQuestionDao fillQuestionDao;

    @Override
    public Map<String, Integer> getCount() {
        Map<String, Integer> map = new HashMap<>(4);
        int fill = fillQuestionDao.getCount();
        int judge = judgeQuestionDao.getCount();
        int single = singleQuestionDao.getCount();
        int multi = multiQuestionDao.getCount();
        map.put("fillCount",fill);
        map.put("judgeCount",judge);
        map.put("singleCount",single);
        map.put("multiCount",multi);
        return map;
    }

    @Override
    public ResultUtil addPaperByRandom(PaperDto paperDto) {
        // 解析随机组卷的内容
        Integer fillNumber = paperDto.getFillNumber();
        Integer judgeNumber = paperDto.getJudgeNumber();
        Integer singleNumber = paperDto.getSingleNumber();
        Integer multiNumber = paperDto.getMultiNumber();
        //判断各种题型是否超出范围
        List<QuestionDto> fills = fillQuestionDao.getListByPage(null);
        if (fills != null && fills.size() < fillNumber){
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"单选题数量最多为："+fills.size());
        }
        List<QuestionDto> judges = judgeQuestionDao.getListByPage(null);
        if (judges != null && judges.size() < judgeNumber){
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"判断题数量最多为："+judges.size());
        }
        List<QuestionDto> singles = singleQuestionDao.getListByPage(null);
        if (singles != null && singles.size() < singleNumber){
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"单选题数量最多为："+singles.size());
        }
        List<QuestionDto> multis = multiQuestionDao.getListByPage(null);
        if (multis != null && multis.size() < multiNumber){
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"多选题数量最多为："+multis.size());
        }
        //开始组卷
        String fillIds = randomListForIds(fills,fillNumber);
        String judgeIds =randomListForIds(judges,judgeNumber);
        String singleIds = randomListForIds(singles,singleNumber);
        String multiIds = randomListForIds(multis,multiNumber);




        //计算总分
        Double totalScore = fillNumber * paperDto.getFillScore() + judgeNumber * paperDto.getJudgeScore() +
                singleNumber * paperDto.getSingleScore() + multiNumber * paperDto.getMultiScore();

        Exam exam = new Exam();
        exam.setId(UUID.randomUUID().toString().replace("-",""));
        exam.setCreateTime(new Date());
        exam.setName(paperDto.getName());
        exam.setExamDesc(paperDto.getExamDesc());
        exam.setFillIds(fillIds);
        exam.setJudgeIds(judgeIds);
        exam.setSingleIds(singleIds);
        exam.setMultiIds(multiIds);
        exam.setTotalTime(paperDto.getTotalTime());
        exam.setFillScore(paperDto.getFillScore());
        exam.setJudgeScore(paperDto.getJudgeScore());
        exam.setSingleScore(paperDto.getSingleScore());
        exam.setMultiScore(paperDto.getMultiScore());
        exam.setTotalScore(totalScore);
        exam.setIsPublish(0);
        int count = examDao.insert(exam);
        if (count > 0){
            return new ResultUtil(EnumCode.OK.getValue(),"随机组卷成功");
        }else {
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"随机组卷失败");
        }
    }

    @Override
    public ResultUtil createPaper(PaperDto paperDto) {
        Integer fillNumber,judgeNumber,singleNumber,multiNumber;
        if ("".equals(paperDto.getFillIds())){
            fillNumber = 0;
        }else {
            fillNumber = paperDto.getFillIds().split(",").length;
        }

        if ("".equals(paperDto.getJudgeIds())){
            judgeNumber = 0;
        }else {
            judgeNumber = paperDto.getJudgeIds().split(",").length;
        }

        if ("".equals(paperDto.getSingleIds())){
            singleNumber = 0;
        }else {
            singleNumber = paperDto.getSingleIds().split(",").length;
        }

        if ("".equals(paperDto.getMultiIds())){
            multiNumber = 0;
        }else {
            multiNumber = paperDto.getMultiIds().split(",").length;
        }

        //计算总分
        Double totalScore = fillNumber * paperDto.getFillScore() + judgeNumber * paperDto.getJudgeScore() +
                singleNumber * paperDto.getSingleScore() + multiNumber * paperDto.getMultiScore();

        Exam exam = new Exam();
        exam.setId(UUID.randomUUID().toString().replace("-",""));
        exam.setCreateTime(new Date());
        exam.setName(paperDto.getName());
        exam.setExamDesc(paperDto.getExamDesc());
        exam.setFillIds(paperDto.getFillIds());
        exam.setJudgeIds(paperDto.getJudgeIds());
        exam.setSingleIds(paperDto.getSingleIds());
        exam.setMultiIds(paperDto.getMultiIds());
        exam.setTotalTime(paperDto.getTotalTime());
        exam.setFillScore(paperDto.getFillScore());
        exam.setJudgeScore(paperDto.getJudgeScore());
        exam.setSingleScore(paperDto.getSingleScore());
        exam.setMultiScore(paperDto.getMultiScore());
        exam.setTotalScore(totalScore);
        exam.setIsPublish(0);
        int count = examDao.insert(exam);
        if (count > 0){
            return new ResultUtil(EnumCode.OK.getValue(),"生成试卷成功");
        }else {
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"生成试卷失败");
        }
    }

    @Override
    public void exportPaper(String id, HttpServletResponse response) {
        Exam exam = this.examDao.queryById(id);
        // 试卷详情
        Map<String, List<?>> detail = this.getExamDetail(exam);
        // 填空题
        List<FillQuestion> fillList = (List<FillQuestion>) detail.get("0");
        // 判断题
        List<JudgeQuestion> judgeList = (List<JudgeQuestion>) detail.get("1");
        // 单选题
        List<SingleQuestion> singleList = (List<SingleQuestion>) detail.get("2");
        // 多选题
        List<MultiQuestion> multiList = (List<MultiQuestion>) detail.get("3");

        String examName = exam.getName();
        String examDesc = exam.getExamDesc();
        Integer totalTime = exam.getTotalTime();
        Double totalScore = exam.getTotalScore();

        XWPFDocument doc = initPaper(exam);

        if (fillList.size() > 0){
            this.createQuestionType(doc,"填空题");
            for (int i=0;i<fillList.size();i++){
                insertQuestion(doc,i,fillList.get(i).getQuestionContent(),null);
            }
        }
        if (judgeList.size() > 0){
            this.createQuestionType(doc,"判断题");
            for (int i=0;i<judgeList.size();i++){
                insertQuestion(doc,i,judgeList.get(i).getQuestionContent(),null);
            }
        }
        if (singleList.size() > 0){
            this.createQuestionType(doc,"单选题");
            Map<String,String> map = new HashMap<>(4);
            for (int i=0;i<singleList.size();i++){
                map.put("A",singleList.get(i).getChoiceA());
                map.put("B",singleList.get(i).getChoiceB());
                map.put("C",singleList.get(i).getChoiceC());
                map.put("D",singleList.get(i).getChoiceD());
                insertQuestion(doc,i,singleList.get(i).getQuestionContent(),map);
            }
        }
        if (multiList.size() > 0){
            this.createQuestionType(doc,"多选题");
            Map<String,String> map = new HashMap<>(4);
            for (int i=0;i<multiList.size();i++){
                map.put("A",multiList.get(i).getChoiceA());
                map.put("B",multiList.get(i).getChoiceB());
                map.put("C",multiList.get(i).getChoiceC());
                map.put("D",multiList.get(i).getChoiceD());
                insertQuestion(doc,i,multiList.get(i).getQuestionContent(),map);
            }
        }
        try {
            doc.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 生成试卷初始化
    private XWPFDocument initPaper(Exam exam){
        XWPFDocument doc = new XWPFDocument();
        // 标题
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText(exam.getName());
        titleRun.setBold(true);
        titleRun.setFontSize(35);
        titleRun.addBreak();
        // 考试介绍
        XWPFParagraph desc = doc.createParagraph();
        desc.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun descRun = desc.createRun();
        descRun.setText(exam.getExamDesc());
        descRun.setBold(true);
        descRun.setFontSize(15);
        descRun.addBreak();
        // 考试时间和总分
        XWPFParagraph score = doc.createParagraph();
        score.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun scoreRun = score.createRun();
        scoreRun.setText("考试时间："+exam.getTotalTime()+" 分钟，   总分："+exam.getTotalScore());
        scoreRun.setBold(true);
        scoreRun.setFontSize(15);
        scoreRun.addBreak();

        return doc;
    }

    // 创建题目类型标题
    private void createQuestionType(XWPFDocument doc,String questionType){
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun xwpfRun = paragraph.createRun();
        xwpfRun.setText(questionType);
        xwpfRun.setBold(true);
        xwpfRun.setFontSize(25);
        xwpfRun.addBreak();
    }

    private void insertQuestion(XWPFDocument doc,Integer index,String questionContent,Map<String,String> choiceMap){
        XWPFParagraph question = doc.createParagraph();
        question.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun questionRun = question.createRun();
        questionRun.setText(index+1+". "+questionContent);
        questionRun.addBreak();
        // 添加选择题选项
        if (choiceMap != null){
            questionRun.setText("A. "+choiceMap.get("A"));
            questionRun.addBreak();
            questionRun.setText("B. "+choiceMap.get("B"));
            questionRun.addBreak();
            questionRun.setText("C. "+choiceMap.get("C"));
            questionRun.addBreak();
            questionRun.setText("C. "+choiceMap.get("D"));
            questionRun.addBreak();
        }
    }

    private String randomListForIds(List<QuestionDto> list,Integer count){
        String firstId = list.remove(new Random().nextInt(list.size())).getId();
        StringBuffer ids = new StringBuffer(firstId);
        for (int i = 1; i < count;i++){
            ids.append(","+list.remove(new Random().nextInt(list.size())).getId());
        }
        return ids.toString();
    }

    @Override
    public Map<String, List<?>> getExamDetail(Exam exam) {
        Map<String, List<?>> map = new HashMap<>(4);
        //解析每种类型题目的ids
        String singleIds = exam.getSingleIds();
        String multiIds = exam.getMultiIds();
        String judgeIds = exam.getJudgeIds();
        String fillIds = exam.getFillIds();
        //填空题
        if (!StringUtils.isEmpty(fillIds)){
            String[] ids = fillIds.split(",");
            List<FillQuestion> fillQuestionList = new ArrayList<>(ids.length);
            FillQuestion fillQuestion = null;
            for (String id : ids){
                fillQuestion = fillQuestionDao.queryById(id);
                fillQuestionList.add(fillQuestion);

            }
            map.put("0",fillQuestionList);
        }else {
            map.put("0",new ArrayList());
        }

        //判断题
        if (!StringUtils.isEmpty(judgeIds)){
            String[] ids = judgeIds.split(",");
            List<JudgeQuestion> judgeQuestionList = new ArrayList<>(ids.length);
            JudgeQuestion judgeQuestion = null;
            for (String id : ids){
                judgeQuestion = judgeQuestionDao.queryById(id);
                judgeQuestionList.add(judgeQuestion);

            }
            map.put("1",judgeQuestionList);
        }else {
            map.put("1",new ArrayList());
        }

        //单选题
        if (!StringUtils.isEmpty(singleIds)){
            String[] ids = singleIds.split(",");
            List<SingleQuestion> singleQuestionList = new ArrayList<>(ids.length);
            SingleQuestion singleQuestion = null;
            for (String id : ids){
                singleQuestion = singleQuestionDao.queryById(id);
                singleQuestionList.add(singleQuestion);

            }
            map.put("2",singleQuestionList);
        }else {
            map.put("2",new ArrayList());
        }
        //多选题
        if (!StringUtils.isEmpty(multiIds)){
            String[] ids = multiIds.split(",");
            List<MultiQuestion> multiQuestionList = new ArrayList<>(ids.length);
            MultiQuestion multiQuestion = null;
            for (String id : ids){
                multiQuestion = multiQuestionDao.queryById(id);
                multiQuestionList.add(multiQuestion);

            }
            map.put("3",multiQuestionList);
        }else {
            map.put("3",new ArrayList());
        }

        return map;
    }

    @Override
    public List<Exam> getListByPage(String name,Integer isPublish) {
        return examDao.getListByPage(name,isPublish);
    }

    @Override
    public Integer updatePaper(PaperDto paperDto) {
        return examDao.updatePaper(paperDto);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Exam queryById(String id) {
        return this.examDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(Exam exam) {
        return this.examDao.insert(exam);
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Exam exam) {
        return this.examDao.update(exam);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Integer deleteById(String id) {
        return this.examDao.deleteById(id);
    }

}