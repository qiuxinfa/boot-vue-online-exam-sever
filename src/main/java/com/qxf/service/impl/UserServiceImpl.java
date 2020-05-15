package com.qxf.service.impl;

import com.qxf.mapper.RoleMapper;
import com.qxf.mapper.UserMapper;
import com.qxf.pojo.Role;
import com.qxf.pojo.User;
import com.qxf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:37
 **/
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user != null){
            //设置角色
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user != null){
            //设置角色
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
    }
}
