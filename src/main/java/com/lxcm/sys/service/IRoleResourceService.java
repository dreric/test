package com.lxcm.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxcm.common.model.TreeNode;
import com.lxcm.sys.entity.RoleResourceEntity;

import java.util.List;

/**
 * @author lx
 * @Classname IRoleResourceService
 * @Description TODO
 * @Date 2020-03-19 10:56
 * @Created by lx
 */
public interface IRoleResourceService extends IService<RoleResourceEntity> {
    /**
     * 根据角色id查询角色拥有的资源树
     * @param roleId
     * @return
     */
    TreeNode getTreeByRoleId(Long roleId);


    /**
     * 保存 给当前角色分配的资源
     * @param roleId
     * @param roleResourceList
     * @return
     */
    boolean save(Long roleId, List<RoleResourceEntity> roleResourceList);
}
