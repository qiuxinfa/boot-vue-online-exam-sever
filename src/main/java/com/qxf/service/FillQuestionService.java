package com.qxf.service;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.FillQuestion;

import java.util.List;

/**
 * 填空题(FillQuestion)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface FillQuestionService {
    int batchInsert(List<FillQuestion> list);

    List<QuestionDto> getListByPage(String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FillQuestion queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FillQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fillQuestion 实例对象
     * @return 实例对象
     */
    FillQuestion insert(FillQuestion fillQuestion);

    /**
     * 修改数据
     *
     * @param fillQuestion 实例对象
     * @return 实例对象
     */
    FillQuestion update(FillQuestion fillQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}