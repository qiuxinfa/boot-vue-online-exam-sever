package com.qxf.service;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.SingleQuestion;

import java.util.List;

/**
 * 单选题(SingleQuestion)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface SingleQuestionService {
    int batchInsert(List<SingleQuestion> list);

    List<QuestionDto> getListByPage(String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SingleQuestion queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SingleQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param singleQuestion 实例对象
     * @return 实例对象
     */
    SingleQuestion insert(SingleQuestion singleQuestion);

    /**
     * 修改数据
     *
     * @param singleQuestion 实例对象
     * @return 实例对象
     */
    SingleQuestion update(SingleQuestion singleQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}