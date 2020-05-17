package com.qxf.service;

import com.qxf.entity.Dict;

import java.util.List;

/**
 * 字典(Dict)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:37
 */
public interface DictService {
    List<Dict> getDictListByDictTypeCode(String dictTypeCode);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Dict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    Dict insert(Dict dict);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    Dict update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}