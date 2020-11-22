package com.qxf.service.impl;

import com.qxf.dao.MultiQuestionDao;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.MultiQuestion;
import com.qxf.service.MultiQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选题(MultiQuestion)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("multiQuestionService")
public class MultiQuestionServiceImpl implements MultiQuestionService {
    @Resource
    private MultiQuestionDao multiQuestionDao;

    @Override
    public int batchInsert(List<MultiQuestion> list) {
        if (CollectionUtils.isEmpty(list)){
            return 0;
        }
        return multiQuestionDao.batchInsert(list);
    }

    @Override
    public List<QuestionDto> getListByPage(String content) {
        return multiQuestionDao.getListByPage(content);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MultiQuestion queryById(String id) {
        return this.multiQuestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MultiQuestion> queryAllByLimit(int offset, int limit) {
        return this.multiQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param multiQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public MultiQuestion insert(MultiQuestion multiQuestion) {
        this.multiQuestionDao.insert(multiQuestion);
        return multiQuestion;
    }

    /**
     * 修改数据
     *
     * @param multiQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public MultiQuestion update(MultiQuestion multiQuestion) {
        this.multiQuestionDao.update(multiQuestion);
        return this.queryById(multiQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.multiQuestionDao.deleteById(id) > 0;
    }
}