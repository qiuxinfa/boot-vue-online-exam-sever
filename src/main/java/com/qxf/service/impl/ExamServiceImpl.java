package com.qxf.service.impl;

import com.github.pagehelper.Page;
import com.qxf.dao.*;
import com.qxf.dto.PaperDto;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.*;
import com.qxf.service.ExamService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
        String fillIds = "";
        String judgeIds = "";
        String singleIds = "";
        String multiIds = "";
        randomListForIds(fillIds,fills,fillNumber);
        randomListForIds(judgeIds,judges,judgeNumber);
        randomListForIds(singleIds,singles,singleNumber);
        randomListForIds(multiIds,multis,multiNumber);
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

    private void randomListForIds(String ids,List<QuestionDto> list,Integer count){
        ids = list.remove(new Random().nextInt(list.size())).getId();
        for (int i = 1; i < count;i++){
            ids += ","+list.remove(new Random().nextInt(list.size())).getId();
        }
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