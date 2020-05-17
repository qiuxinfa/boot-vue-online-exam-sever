package com.qxf.service.impl;

import com.qxf.dao.ExamRecordDao;
import com.qxf.entity.ExamRecord;
import com.qxf.service.ExamRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试记录表(ExamRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-05-17 11:25:40
 */
@Service("examRecordService")
public class ExamRecordServiceImpl implements ExamRecordService {
    @Resource
    private ExamRecordDao examRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamRecord queryById(String id) {
        return this.examRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ExamRecord> queryAllByLimit(int offset, int limit) {
        return this.examRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param examRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ExamRecord insert(ExamRecord examRecord) {
        this.examRecordDao.insert(examRecord);
        return examRecord;
    }

    /**
     * 修改数据
     *
     * @param examRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ExamRecord update(ExamRecord examRecord) {
        this.examRecordDao.update(examRecord);
        return this.queryById(examRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.examRecordDao.deleteById(id) > 0;
    }
}