package com.lxcm.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxcm.common.model.TreeNode;
import com.lxcm.sys.entity.UserEntity;

import java.util.List;

/**
 * @Classname IUserService
 * @Description TODO
 * @Date 2020-03-16 16:34
 * @Created by lx
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 根据用户id获取用户的菜单树
     * @param id
     * @return
     */
    List<TreeNode> getMenuTreeByUserId(Long id);
}
