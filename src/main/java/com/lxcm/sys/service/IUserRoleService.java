package com.lxcm.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxcm.sys.entity.UserRoleEntity;

import java.util.List;

/**
 * @Classname IUserRoleService
 * @Description TODO
 * @Date 2020-03-19 10:57
 * @Created by lx
 */
public interface IUserRoleService extends IService<UserRoleEntity> {
    /**
     * 根据用户id查询用户拥有的角色
     * @param userId
     * @return
     */
    List<UserRoleEntity> getByUserId(Long userId);

    /**
     * 保存给当前用户分配的角色
     * @param userId
     * @param roleIdList
     * @return
     */
    boolean save(Long userId, List<Long> roleIdList);
}
