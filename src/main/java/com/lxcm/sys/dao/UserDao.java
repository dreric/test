package com.lxcm.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxcm.sys.entity.MenuEntity;
import com.lxcm.sys.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @Classname UserDao
 * @Description TODO
 * @Date 2020-03-16 16:32
 * @Created by lx
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    Set<String> selectUserRoleNameSet(Long id);
    /**
     * 根据id查询用户权限名称的集合
     * @param id
     * @return
     */
    Set<String> selectUserPermissionNameSet(Long id);


    /**
     * 根据用户id查询用户菜单
     * @param id
     * @return
     */
    List<MenuEntity> selectMenuList(Long id);
}
