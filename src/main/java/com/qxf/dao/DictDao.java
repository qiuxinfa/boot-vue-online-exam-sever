package com.qxf.dao;

import com.qxf.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典(Dict)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 11:25:35
 */
public interface DictDao {

    List<Dict> getDictListByDictTypeCode(String dictTypeCode);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Dict> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dict 实例对象
     * @return 对象列表
     */
    List<Dict> queryAll(Dict dict);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int insert(Dict dict);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}