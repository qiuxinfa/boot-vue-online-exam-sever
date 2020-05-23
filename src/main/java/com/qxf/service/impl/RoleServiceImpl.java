package com.qxf.service.impl;

import com.qxf.dao.RoleDao;
import com.qxf.entity.Role;
import com.qxf.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/23 16:28
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }
}
