package com.lxcm.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxcm.sys.entity.RoleResourceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname RoleSourceDao
 * @Description TODO 角色与菜单对应关系
 * @Date 2020-03-19 10:08
 * @Created by lx
 */
@Mapper
public interface RoleResourceDao extends BaseMapper<RoleResourceEntity> {
    /**
     * 通过角色id查询角色拥有的的资源信息
     * 使用左外连接查询，角色未分配的资源信息一并显示出来
     * @param roleId
     * @return
     */
    List<RoleResourceEntity> selectByRoleId(Long roleId);
}
