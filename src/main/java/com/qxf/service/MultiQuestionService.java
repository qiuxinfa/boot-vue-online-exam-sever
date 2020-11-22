package com.qxf.service;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.MultiQuestion;

import java.util.List;

/**
 * 多选题(MultiQuestion)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface MultiQuestionService {
    int batchInsert(List<MultiQuestion> list);
    List<QuestionDto> getListByPage(String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MultiQuestion queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MultiQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param multiQuestion 实例对象
     * @return 实例对象
     */
    MultiQuestion insert(MultiQuestion multiQuestion);

    /**
     * 修改数据
     *
     * @param multiQuestion 实例对象
     * @return 实例对象
     */
    MultiQuestion update(MultiQuestion multiQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}