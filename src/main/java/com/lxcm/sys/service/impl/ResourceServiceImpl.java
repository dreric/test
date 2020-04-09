package com.lxcm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxcm.common.model.TreeNode;
import com.lxcm.common.util.TreeUtil;
import com.lxcm.sys.dao.ResourceDao;
import com.lxcm.sys.entity.ResourceEntity;
import com.lxcm.sys.service.IResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lx
 * @Classname ResourceServiceImpl
 * @Description TODO
 * @Date 2020-03-19 10:37
 * @Created by lx
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, ResourceEntity> implements IResourceService {

    @Override
    public TreeNode getTreeById(Long id) {
        ResourceEntity r = this.getById(id);
        //1.查询所有节点
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("path", r.getPath());
        queryWrapper.orderByAsc("order_num");
        List<ResourceEntity> list = this.list(queryWrapper);

        //2.对象数据转换
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (ResourceEntity resource : list) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(resource.getResourceId());
            treeNode.setName(resource.getName());
            treeNode.setParentId(resource.getParentId());
            treeNodeList.add(treeNode);
        }
        TreeUtil treeUtil = new TreeUtil(treeNodeList);
        TreeNode treeNode = treeUtil.generateTree(id);
        return treeNode;
    }
}
