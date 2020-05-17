package com.qxf.service.impl;

import com.qxf.dao.DictDao;
import com.qxf.entity.Dict;
import com.qxf.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典(Dict)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:38
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;

    @Override
    public List<Dict> getDictListByDictTypeCode(String dictTypeCode) {
        return dictDao.getDictListByDictTypeCode(dictTypeCode);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dict queryById(String id) {
        return this.dictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Dict> queryAllByLimit(int offset, int limit) {
        return this.dictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict insert(Dict dict) {
        this.dictDao.insert(dict);
        return dict;
    }

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public Dict update(Dict dict) {
        this.dictDao.update(dict);
        return this.queryById(dict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.dictDao.deleteById(id) > 0;
    }
}