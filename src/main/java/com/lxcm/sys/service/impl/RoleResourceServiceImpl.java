package com.lxcm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxcm.common.model.TreeNode;
import com.lxcm.common.util.TreeUtil;
import com.lxcm.sys.dao.RoleResourceDao;
import com.lxcm.sys.entity.RoleResourceEntity;
import com.lxcm.sys.service.IRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname RoleResourceServiceImpl
 * @Description TODO
 * @Date 2020-03-19 11:36
 * @Created by lx
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceDao, RoleResourceEntity> implements IRoleResourceService {
    @Autowired
    RoleResourceDao dao;

    @Override
    public TreeNode getTreeByRoleId(Long roleId) {
        List<RoleResourceEntity> list = dao.selectByRoleId(roleId);
        //对象数据转换
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for(RoleResourceEntity rr:list){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(rr.getId());
            treeNode.setParentId(rr.getParentId());
            treeNode.setName(rr.getName());
            // 设置节点的选中状态
            Map<String, Boolean> state = new HashMap<String, Boolean>();
            if (rr.getRoleId() == null) {
                state.put("checked", false);
            } else {
                state.put("checked", true);
            }
            treeNode.setState(state);
            treeNodeList.add(treeNode);
        }
        // 3.生成菜单树
        TreeUtil treeUtil = new TreeUtil(treeNodeList);
        TreeNode treeNode = treeUtil.generateTree(0L);
        return treeNode;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Long roleId, List<RoleResourceEntity> roleResourceList) {
        //1.删除该角色已经分配的资源
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        this.remove(queryWrapper);

        //2.保存新分配的资源到sys_role_resource
        this.saveBatch(roleResourceList);
        return true;
    }
}
