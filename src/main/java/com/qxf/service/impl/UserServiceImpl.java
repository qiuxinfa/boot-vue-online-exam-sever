package com.qxf.service.impl;

import com.qxf.dao.RoleDao;
import com.qxf.dao.UserDao;

import com.qxf.entity.Role;
import com.qxf.entity.User;
import com.qxf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:37
 **/
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user != null){
            //设置角色
            List<Role> roles = roleDao.getRolesByUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public List<User> getListByPage(String name) {
        return userDao.getListByPage(name);
    }

    @Override
    public Integer deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer addUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        return userDao.addUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if (user != null){
            //设置角色
            List<Role> roles = roleDao.getRolesByUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
    }
}
