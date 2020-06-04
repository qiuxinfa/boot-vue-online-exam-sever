package com.qxf.service.impl;

import com.qxf.dao.PermissionDao;
import com.qxf.dao.RoleDao;
import com.qxf.dao.UserDao;

import com.qxf.dao.UserRoleDao;
import com.qxf.entity.Permission;
import com.qxf.entity.Role;
import com.qxf.entity.User;
import com.qxf.entity.UserRole;
import com.qxf.service.UserService;
import com.qxf.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user != null){
            UserInfoUtil.setUserIdByUsername(username,user.getId());
            //设置角色
            List<Role> roles = roleDao.getRolesByUserId(user.getId());
            user.setRoles(roles);

            // 设置权限，实现基于url的访问控制
            List<Permission> permissions = new ArrayList<>();
            for (Role role : roles){
                permissions.addAll(permissionDao.getPermissionListByRoleId(role.getId()));
            }
            user.setPermissions(permissions);
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
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        //设置用户角色关联表
        UserRole userRole = new UserRole();
        userRole.setId(UUID.randomUUID().toString().replace("-",""));
        userRole.setUserId(user.getId());
        userRole.setRoleId(user.getRoleName());
        userRoleDao.insert(userRole);
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
