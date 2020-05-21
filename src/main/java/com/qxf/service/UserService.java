package com.qxf.service;

import com.qxf.entity.User;

import java.util.List;

public interface UserService {
    List<User> getListByPage(String name);
    Integer deleteUser(String id);
    Integer addUser(User user);
    Integer updateUser(User user);
    User getUserByUsername(String username);
}
