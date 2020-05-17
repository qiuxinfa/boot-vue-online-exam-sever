package com.qxf.service;

import com.qxf.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserByUsername(String username);
}
