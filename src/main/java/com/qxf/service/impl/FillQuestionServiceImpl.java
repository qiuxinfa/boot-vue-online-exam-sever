package com.qxf.service.impl;

import com.qxf.dao.FillQuestionDao;
import com.qxf.dto.QuestionDto;
import com.qxf.entity.FillQuestion;
import com.qxf.service.FillQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 填空题(FillQuestion)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("fillQuestionService")
public class FillQuestionServiceImpl implements FillQuestionService {
    @Resource
    private FillQuestionDao fillQuestionDao;

    @Override
    public int batchInsert(List<FillQuestion> list) {
        if (CollectionUtils.isEmpty(list)){
            return 0;
        }
        return fillQuestionDao.batchInsert(list);
    }

    @Override
    public List<QuestionDto> getListByPage(String content) {
        return fillQuestionDao.getListByPage(content);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FillQuestion queryById(String id) {
        return this.fillQuestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FillQuestion> queryAllByLimit(int offset, int limit) {
        return this.fillQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fillQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public FillQuestion insert(FillQuestion fillQuestion) {
        this.fillQuestionDao.insert(fillQuestion);
        return fillQuestion;
    }

    /**
     * 修改数据
     *
     * @param fillQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public FillQuestion update(FillQuestion fillQuestion) {
        this.fillQuestionDao.update(fillQuestion);
        return this.queryById(fillQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.fillQuestionDao.deleteById(id) > 0;
    }
}