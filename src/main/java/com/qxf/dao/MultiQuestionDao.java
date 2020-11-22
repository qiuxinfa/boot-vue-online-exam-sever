package com.qxf.dao;

import com.qxf.dto.QuestionDto;
import com.qxf.entity.MultiQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 多选题(MultiQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface MultiQuestionDao {
    int batchInsert(List<MultiQuestion> list);

    int getCount();

    List<QuestionDto> getListByPage(@Param("content") String content);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MultiQuestion queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MultiQuestion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param multiQuestion 实例对象
     * @return 对象列表
     */
    List<MultiQuestion> queryAll(MultiQuestion multiQuestion);

    /**
     * 新增数据
     *
     * @param multiQuestion 实例对象
     * @return 影响行数
     */
    int insert(MultiQuestion multiQuestion);

    /**
     * 修改数据
     *
     * @param multiQuestion 实例对象
     * @return 影响行数
     */
    int update(MultiQuestion multiQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}