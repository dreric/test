package com.lxcm.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxcm.sys.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname RoleDao
 * @Description TODO 角色管理
 * @Date 2020-03-19 10:07
 * @Created by lx
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
}
