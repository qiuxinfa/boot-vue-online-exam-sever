package com.qxf.dao;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.SingleQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单选题(SingleQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface SingleQuestionDao {
    int batchInsert(List<SingleQuestion> list);

    int getCount();

    List<QuestionDto> getListByPage(@Param("content") String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SingleQuestion queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SingleQuestion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param singleQuestion 实例对象
     * @return 对象列表
     */
    List<SingleQuestion> queryAll(SingleQuestion singleQuestion);

    /**
     * 新增数据
     *
     * @param singleQuestion 实例对象
     * @return 影响行数
     */
    int insert(SingleQuestion singleQuestion);

    /**
     * 修改数据
     *
     * @param singleQuestion 实例对象
     * @return 影响行数
     */
    int update(SingleQuestion singleQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}