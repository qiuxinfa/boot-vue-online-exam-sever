package com.qxf.service;

import com.github.pagehelper.Page;
import com.qxf.dto.PaperDto;
import com.qxf.entity.Exam;
import com.qxf.util.ResultUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 考试安排(Exam)表服务接口
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
public interface ExamService {
    Map<String,Integer> getCount();

    ResultUtil addPaperByRandom(PaperDto paperDto);
    ResultUtil createPaper(PaperDto paperDto);
    void exportPaper(String id, HttpServletResponse response);
    Map<String,List<?>> getExamDetail(Exam exam);

    List<Exam> getListByPage(String name,Integer isPublish);

    Integer updatePaper(PaperDto paperDto);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Exam queryById(String id);


    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    Integer insert(Exam exam);

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    Integer update(Exam exam);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Integer deleteById(String id);

}