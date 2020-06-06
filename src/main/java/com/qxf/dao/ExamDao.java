package com.qxf.dao;

import com.github.pagehelper.Page;
import com.qxf.dto.PaperDto;
import com.qxf.entity.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试安排(Exam)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface ExamDao {
    /*
     * @Author qiuxinfa
     * @Description 分页查询考试列表或试卷列表
     * @Date  2020/5/26 23:24
     * @Param [name]
     * @return java.util.List<com.qxf.entity.Exam>
     **/
    List<Exam> getListByPage(@Param("name") String name,@Param("isPublish") Integer isPublish);
    Integer updatePaper(PaperDto paperDto);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Exam queryById(String id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param exam 实例对象
     * @return 对象列表
     */
    List<Exam> queryAll(Exam exam);

    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 影响行数
     */
    int insert(Exam exam);

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 影响行数
     */
    int update(Exam exam);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}