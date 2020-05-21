package com.qxf.service;

import com.github.pagehelper.Page;
import com.qxf.dto.ExamRecordDto;
import com.qxf.entity.ExamRecord;

import java.util.List;

/**
 * 考试记录表(ExamRecord)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface ExamRecordService {

    List<ExamRecordDto> getListByPage(String name, String userId);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamRecord queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExamRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param examRecord 实例对象
     * @return 实例对象
     */
    Integer insert(ExamRecord examRecord);

    /**
     * 修改数据
     *
     * @param examRecord 实例对象
     * @return 实例对象
     */
    ExamRecord update(ExamRecord examRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}