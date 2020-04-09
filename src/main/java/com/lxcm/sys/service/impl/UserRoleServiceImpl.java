package com.lxcm.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxcm.sys.dao.UserRoleDao;
import com.lxcm.sys.entity.UserRoleEntity;
import com.lxcm.sys.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserRoleServiceImpl
 * @Description TODO
 * @Date 2020-03-19 14:42
 * @Created by lx
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements IUserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public List<UserRoleEntity> getByUserId(Long userId) {

        return userRoleDao.selectByUserId(userId);
    }

    @Override
    public boolean save(Long userId, List<Long> roleIdList) {
        return false;
    }
}
