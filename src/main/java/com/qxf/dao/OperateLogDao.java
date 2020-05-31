package com.qxf.dao;

import com.qxf.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OperateLogDao {
    @Insert("insert into operate_log values(#{id},#{requestUrl},#{remoteAddr},#{params},#{method},#{createTime},#{userId},#{isSuccess})")
    Integer addOperateLog(OperateLog operateLog);

    @Select("select * from operate_log order by create_time desc")
    List<OperateLog> getOperateLogList();
}
