package com.qxf.service.impl;

import com.qxf.dao.SingleQuestionDao;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.SingleQuestion;
import com.qxf.service.SingleQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单选题(SingleQuestion)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("singleQuestionService")
public class SingleQuestionServiceImpl implements SingleQuestionService {
    @Resource
    private SingleQuestionDao singleQuestionDao;

    @Override
    public int batchInsert(List<SingleQuestion> list) {
        if (CollectionUtils.isEmpty(list)){
            return 0;
        }
        return singleQuestionDao.batchInsert(list);
    }

    @Override
    public List<QuestionDto> getListByPage(String content) {
        return singleQuestionDao.getListByPage(content);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SingleQuestion queryById(String id) {
        return this.singleQuestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SingleQuestion> queryAllByLimit(int offset, int limit) {
        return this.singleQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param singleQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public SingleQuestion insert(SingleQuestion singleQuestion) {
        this.singleQuestionDao.insert(singleQuestion);
        return singleQuestion;
    }

    /**
     * 修改数据
     *
     * @param singleQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public SingleQuestion update(SingleQuestion singleQuestion) {
        this.singleQuestionDao.update(singleQuestion);
        return this.queryById(singleQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.singleQuestionDao.deleteById(id) > 0;
    }
}