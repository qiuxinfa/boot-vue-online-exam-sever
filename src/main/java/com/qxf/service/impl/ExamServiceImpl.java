package com.qxf.service.impl;

import com.github.pagehelper.Page;
import com.qxf.dao.*;
import com.qxf.entity.*;
import com.qxf.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Exam> getListByPage(String name) {
        return examDao.getListByPage(name);
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Exam> queryAllByLimit(int offset, int limit) {
        return this.examDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Exam insert(Exam exam) {
        this.examDao.insert(exam);
        return exam;
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Exam update(Exam exam) {
        this.examDao.update(exam);
        return this.queryById(exam.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.examDao.deleteById(id) > 0;
    }
}