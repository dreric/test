package com.lxcm.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxcm.common.model.TreeNode;
import com.lxcm.sys.entity.ResourceEntity;

/**
 * @Classname IResourceService
 * @Description TODO
 * @Date 2020-03-19 10:36
 * @Created by lx
 */
public interface IResourceService extends IService<ResourceEntity> {
    /**
     * 通过id查询该节点的树形结构数据
     *
     * @param
     * @return
     */
    TreeNode getTreeById(Long id);
}
