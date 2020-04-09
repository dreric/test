package com.lxcm.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxcm.sys.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname UserRoleDao
 * @Description TODO 用户与角色对应关系
 * @Date 2020-03-19 9:43
 * @Created by lx
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

    /**
     * 通过用户id查询用户拥有的的角色信息
     * 使用左外连接查询，用户未分配的角色信息一并显示出来
     * @param userId
     * @return
     */
    List<UserRoleEntity> selectByUserId(Long userId);
}
