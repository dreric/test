package com.lxcm.common.util;

import com.lxcm.common.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TreeUtil
 * @Description TODO 树操作工具类
 * @Date 2020-03-16 15:52
 * @Created by lx
 */
public class TreeUtil {
    private List<TreeNode> treeNodeList;

    public TreeUtil(List<TreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }


    /**
     * 根据节点id获取节点对象信息
     *
     * @param id
     * @return
     */
    private TreeNode getById(Long id) {
        //遍历所有的节点
        for (TreeNode treeNode : treeNodeList) {
            if (treeNode.getId().equals(id)) {
                return treeNode;
            }
        }
        return null;
    }


    /**
     * 根据节点id查询儿子节点的集合
     *
     * @param id
     * @return
     */
    private List<TreeNode> getChildrenById(Long id) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodeList) {
            if (treeNode.getParentId().equals(id)) {
                children.add(treeNode);
            }
        }
        return children;
    }


    /**
     * 生成树形结构数据
     * @param id
     * @return
     */
    public TreeNode generateTree(Long id) {
        //1.根据节点id查询该节点信息
        TreeNode treeNode = getById(id);

        //2.根据节点id查询该节点下所有的子节点
        List<TreeNode> children = getChildrenById(id);
        //3.遍历子节点的 儿子节点
        for (TreeNode node : children) {
            TreeNode child = generateTree(node.getId());
            treeNode.getChildren().add(child);
        }
        return treeNode;
    }
}
