package com.qxf.dao;

import com.github.pagehelper.Page;
import com.qxf.dto.ExamRecordDto;
import com.qxf.entity.ExamRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试记录表(ExamRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface ExamRecordDao {
    /*
     * @Author qiuxinfa
     * @Description 分页查询考试记录
     * @Date  2020/5/20 19:30
     * @Param [page, name]
     * @return java.util.List<com.qxf.entity.ExamRecord>
     **/
    List<ExamRecordDto> getListByPage(@Param("name") String name, @Param("userId") String userId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamRecord queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExamRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param examRecord 实例对象
     * @return 对象列表
     */
    List<ExamRecord> queryAll(ExamRecord examRecord);

    /**
     * 新增数据
     *
     * @param examRecord 实例对象
     * @return 影响行数
     */
    int insert(ExamRecord examRecord);

    /**
     * 修改数据
     *
     * @param examRecord 实例对象
     * @return 影响行数
     */
    int update(ExamRecord examRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}