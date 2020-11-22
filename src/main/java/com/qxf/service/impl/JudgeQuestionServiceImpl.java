package com.qxf.service.impl;

import com.qxf.dao.JudgeQuestionDao;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.JudgeQuestion;
import com.qxf.service.JudgeQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题(JudgeQuestion)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("judgeQuestionService")
public class JudgeQuestionServiceImpl implements JudgeQuestionService {
    @Resource
    private JudgeQuestionDao judgeQuestionDao;

    @Override
    public int batchInsert(List<JudgeQuestion> list) {
        if (CollectionUtils.isEmpty(list)){
            return 0;
        }
        return judgeQuestionDao.batchInsert(list);
    }

    @Override
    public List<QuestionDto> getListByPage(String content) {
        return judgeQuestionDao.getListByPage(content);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JudgeQuestion queryById(String id) {
        return this.judgeQuestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<JudgeQuestion> queryAllByLimit(int offset, int limit) {
        return this.judgeQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param judgeQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public JudgeQuestion insert(JudgeQuestion judgeQuestion) {
        this.judgeQuestionDao.insert(judgeQuestion);
        return judgeQuestion;
    }

    /**
     * 修改数据
     *
     * @param judgeQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public JudgeQuestion update(JudgeQuestion judgeQuestion) {
        this.judgeQuestionDao.update(judgeQuestion);
        return this.queryById(judgeQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.judgeQuestionDao.deleteById(id) > 0;
    }
}