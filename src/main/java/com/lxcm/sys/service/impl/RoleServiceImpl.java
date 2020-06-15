package com.lxcm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxcm.sys.dao.RoleDao;
import com.lxcm.sys.entity.RoleEntity;
import com.lxcm.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @Classname RoleServiceImpl
 * @Description TODO 角色服务实现
 * @Date 2020-03-19 12:03
 * @Created by lx
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements IRoleService {

}
