package com.qxf.dao;

import com.qxf.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> getListByPage(@Param("username") String name);
    Integer deleteUser(String id);
    Integer addUser(User user);
    Integer updateUser(User user);
    User getUserByUsername(String username);
}
