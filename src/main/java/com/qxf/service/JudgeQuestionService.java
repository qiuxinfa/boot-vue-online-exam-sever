package com.qxf.service;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.JudgeQuestion;

import java.util.List;

/**
 * 判断题(JudgeQuestion)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface JudgeQuestionService {
    int batchInsert(List<JudgeQuestion> list);

    List<QuestionDto> getListByPage(String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JudgeQuestion queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<JudgeQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param judgeQuestion 实例对象
     * @return 实例对象
     */
    JudgeQuestion insert(JudgeQuestion judgeQuestion);

    /**
     * 修改数据
     *
     * @param judgeQuestion 实例对象
     * @return 实例对象
     */
    JudgeQuestion update(JudgeQuestion judgeQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}