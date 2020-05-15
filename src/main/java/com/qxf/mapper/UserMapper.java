package com.qxf.mapper;

import com.qxf.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserByUsername(String username);
    List<User> getUserList();
}
