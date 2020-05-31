package com.qxf.dao;

import com.qxf.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginLogDao {
    @Insert("insert into login_log values(#{id},#{userId},#{loginTime},#{ip})")
    Integer addLoginLog(LoginLog loginLog);

    @Select("select * from login_log order by login_time desc")
    List<LoginLog> getLoginLogList();
}
